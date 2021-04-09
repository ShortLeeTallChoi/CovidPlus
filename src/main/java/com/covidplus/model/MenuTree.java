package com.covidplus.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.web.firewall.FirewalledRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuTree {
	String menu_name;
	String menu_id;
	String parent_menu_id;
	int menu_depth;
	int menu_order;
	String menu_path;
	
	List<String> auth_id = new ArrayList<String>();
	static HashMap<String,MenuTree> all_menu_list = new HashMap<String,MenuTree>();

	MenuTree sibling_menu;
	MenuTree child_menu;
	
	static int totalNodeCount = 0;
	
	public MenuTree() {
		menu_name = "CovidPlus";
		menu_depth = 0;
		menu_order = 0;
		menu_id = "root";
		parent_menu_id = "root";
		menu_path = "root";
		totalNodeCount++;
		all_menu_list.put("root", this);
	}
	
	public MenuTree(String menu_name, String menu_id, String parent_menu_id, int menu_depth, int menu_order, String menu_path) {
		super();
		this.menu_name = menu_name;
		this.menu_id = menu_id;
		this.parent_menu_id = parent_menu_id;
		this.menu_depth = menu_depth;
		this.menu_order = menu_order;
		this.menu_path = menu_path;
		totalNodeCount++;
		all_menu_list.put(menu_id, this);
	}
	
	public MenuTree(MenuVO menuVO) {
		super();
		this.menu_name = menuVO.getMenu_name();
		this.menu_id = menuVO.getMenu_id();
		this.parent_menu_id = menuVO.getParent_menu_id();
		this.menu_depth = menuVO.getMenu_depth();
		this.menu_order = menuVO.getMenu_order();
		this.menu_path = menuVO.getMenu_path();
		totalNodeCount++;
		all_menu_list.put(menu_id, this);
	}

	public void makeSiblingMenu(String menu_name, String menu_id, String parent_menu_id, int menu_depth, int menu_order, String menu_path, MenuTree travelNode) {
		MenuTree parentNode = null;
		if(travelNode.menu_id.equals("root")) {
			parentNode = travelNode;
			travelNode = travelNode.child_menu;
		}
		
		if(travelNode == null) {
			parentNode.child_menu = travelNode = new MenuTree(menu_name, menu_id, parent_menu_id, menu_depth, menu_order, menu_path);
		}else if(travelNode.menu_depth == menu_depth) {
			lastNode(travelNode).sibling_menu = new MenuTree(menu_name, menu_id, parent_menu_id, menu_depth, menu_order, menu_path);
		}
	}
	
	public void addMenu(String menu_name, String menu_id, String parent_menu_id, int menu_order, String menu_path) {
		MenuTree travelNode = all_menu_list.get(parent_menu_id);
		if(travelNode.child_menu == null) {
			travelNode.child_menu = new MenuTree(menu_name, menu_id, parent_menu_id, travelNode.menu_depth+1, menu_order, menu_path);
		}else {
			makeSiblingMenu(menu_name, menu_id, parent_menu_id, travelNode.menu_depth+1, menu_order, menu_path,travelNode.child_menu);
		}
	}
	
	public MenuTree lastNode(MenuTree travelNode) {
		if(travelNode.sibling_menu != null) {
			return lastNode(travelNode.sibling_menu);
		}else {
			return travelNode;
		}
	}
	
	public static String toJSListHtml() {
		String html = null;
		MenuTree travelNode = all_menu_list.get("root");
		childTravel(travelNode, travelNode.menu_depth);
		return html;
	}

	/*
	 * private static void childTravel(MenuTree travelNode) { if(travelNode == null)
	 * return; for (int i = 0; i < travelNode.menu_depth; i++) {
	 * System.out.print("-"); } System.out.println(travelNode.menu_id);
	 * 
	 * childTravel(travelNode.child_menu); childTravel(travelNode.sibling_menu); }
	 */
	
	private static void childTravel(MenuTree travelNode, int depth) {
		if(travelNode == null) return;
		tab(travelNode,depth);
		childTravel(travelNode.child_menu, travelNode.menu_depth);
		childTravel(travelNode.sibling_menu, travelNode.menu_depth);
		if(travelNode.menu_depth > depth) {
			for (int i = 0; i < travelNode.menu_depth; i++) {
				System.out.print("\t");
			}
			System.out.println("</ul>");
			for (int i = 1; i < travelNode.menu_depth; i++) {
				System.out.print("\t");
			}
			System.out.println("</li>");
		}
	}
	
	public static void tab(MenuTree travelNode, int depth) {
		if (travelNode.menu_depth > depth) {
			for (int i = 0; i < travelNode.menu_depth; i++) {
				System.out.print("\t");
			}
			System.out.println("<ul>");
		}
		 
		for (int i = 0; i < travelNode.menu_depth; i++) {
			System.out.print("\t");
		}
		System.out.print("<li><i class=\"far fa-file-alt\" aria-hidden=\"true\"></i>"+travelNode.menu_id);
		if(travelNode.child_menu != null) {
			System.out.println();
		}else {
			System.out.println("</li>");
		}
	}
	
	static String html = "";
	public static void toIndexMenuHtml() {
		
		MenuTree travelNode = all_menu_list.get("root");
		htmlWrite(travelNode);
	}
	
	public static void htmlWrite(MenuTree travelNode) {
		
		if(travelNode.menu_depth == 2) {
			inputDepth2Name(travelNode.menu_name);
			depth2HtmlCollect(travelNode.child_menu);
		}
		htmlWrite(travelNode.child_menu);
		htmlWrite(travelNode.sibling_menu);
	}
	
	public static String depth2HtmlCollect(MenuTree travelNode) {
		String returnHtml = "";
		if(travelNode != null) {
			for (MenuTree tempNode = travelNode; tempNode.sibling_menu != null; tempNode = tempNode.sibling_menu) {
				returnHtml = returnHtml+inputDepth2NavPathName(tempNode.menu_path, tempNode.menu_name);
			}
			returnHtml = inputDepth2NavHtml(returnHtml);
		}
		return returnHtml;
	}
	
	// -- 이하 개발중
	public void addMenu(List<MenuVO> menuList) {
		Arrays.sort(menuList.toArray());
		for (MenuVO menuVO : menuList) {
			new MenuTree(menuVO);
		}
	}
	
	public static void makeTree() {
		for (String key : all_menu_list.keySet()) {
			if(all_menu_list.get(key).getParent_menu_id().equals("root")) {
				all_menu_list.get("root");
			}
		}
	}	
	
	public void addChild(MenuTree parentNode, MenuTree childNode) {
		if(parentNode.child_menu == null) {
			parentNode.child_menu = childNode;
		}else {
			makeSiblingMenu(parentNode.child_menu, childNode);
		}
	}
	
	public void makeSiblingMenu(MenuTree travelNode, MenuTree addNode) {
		lastNode(travelNode).sibling_menu = addNode;
	}
	
	public static void main(String[] args) {
		MenuTree menuTree = new MenuTree();
		menuTree.addMenu("1번 부모메뉴", "perent01", "root", 0, "");
		menuTree.addMenu("2번 부모메뉴", "perent02", "root", 0, "");
		menuTree.addMenu("3번 부모메뉴", "perent03", "root", 0, "");
		menuTree.addMenu("4번 부모메뉴", "perent04", "root", 0, "");
		menuTree.addMenu("5번 부모메뉴", "perent05", "root", 0, "");
		menuTree.addMenu("1번 자식메뉴1", "child1-1", "perent01", 0, "");
		menuTree.addMenu("2번 자식메뉴1", "child2-1", "perent02", 0, "");
		menuTree.addMenu("2번 자식메뉴1", "child2-2", "perent02", 0, "");
		menuTree.addMenu("2번 자식메뉴1", "child2-3", "perent02", 0, "");
		menuTree.addMenu("3번 자식메뉴1", "child3-1", "perent03", 0, "");
		menuTree.addMenu("2번 자식메뉴1 자식메뉴1", "child2-1-1", "child2-1", 0, "");
		menuTree.addMenu("2번 자식메뉴1 자식메뉴2", "child2-1-2", "child2-1", 0, "");
		MenuTree.toJSListHtml();
	}
	
	public String inputDepth1Name(String name) {
		String depth_1_html = "<a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapsePages\" aria-expanded=\"false\" aria-controls=\"collapsePages\">\r\n" + 
				"                                <div class=\"sb-nav-link-icon\"><i class=\"fas fa-book-open\"></i></div>\r\n" + 
				"                                #{name}#\r\n" + 
				"                                <div class=\"sb-sidenav-collapse-arrow\"><i class=\"fas fa-angle-down\"></i></div>\r\n" + 
				"                            </a>";
		depth_1_html.replace("#{name}#", name);
		return depth_1_html;
	}

	public String inputDepth2Html(String html) {
		String depth_2_outline_html = "<div class=\"collapse\" id=\"collapsePages\" aria-labelledby=\"headingTwo\" data-parent=\"#sidenavAccordion\">\r\n" + 
				"                                <nav class=\"sb-sidenav-menu-nested nav accordion\" id=\"sidenavAccordionPages\">"
				+ 								"#{html}#"
				+ 								"</nav>\r\n" + 
				"                      </div>";
		depth_2_outline_html.replace("#{html}#", html);
		return depth_2_outline_html;
	}

	public static String inputDepth2Name(String name) {
		String depth_2_inner_name_html = "<a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#pagesCollapseAuth\" aria-expanded=\"false\" aria-controls=\"pagesCollapseAuth\">\r\n" + 
				"                                        #{name}#\r\n" + 
				"                                        <div class=\"sb-sidenav-collapse-arrow\"><i class=\"fas fa-angle-down\"></i></div>\r\n" + 
				"                                    </a>";
		depth_2_inner_name_html.replace("#{name}#", name);
		return depth_2_inner_name_html;
	}
	
	public static String inputDepth2NavHtml(String html) {
		String depth_2_inner_nav_outline_html = "<div class=\"collapse\" id=\"pagesCollapseAuth\" aria-labelledby=\"headingOne\" data-parent=\"#sidenavAccordionPages\">\r\n" + 
				"                                        <nav class=\"sb-sidenav-menu-nested nav\">\r\n" + 
				"                                            #{html}#\r\n" + 
				"                                        </nav>\r\n" + 
				"                                    </div>";
		depth_2_inner_nav_outline_html.replace("#{html}#", html);
		return depth_2_inner_nav_outline_html;
	}

	public static String inputDepth2NavPathName(String path, String name) {
		String depth_2_inner_nav_menu_html = "<a class=\"nav-link\" href=\"#{path}#\">#{name}#</a>\r\n";
		depth_2_inner_nav_menu_html.replace("#{path}#", path).replace("#{name}#", name);
		return depth_2_inner_nav_menu_html;
	}
}