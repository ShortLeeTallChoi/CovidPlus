package com.covidplus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public static void allNodeTravel() {
		MenuTree travelNode = all_menu_list.get("root");
		childTravel(travelNode);
	}

	private static void childTravel(MenuTree travelNode) {
		if(travelNode == null) return;
		for (int i = 0; i < travelNode.menu_depth; i++) {
			System.out.print("-");
		}
		System.out.println(travelNode.menu_id);
		
		childTravel(travelNode.child_menu);
		childTravel(travelNode.sibling_menu);
	}
}
