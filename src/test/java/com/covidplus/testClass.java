package com.covidplus;

import com.covidplus.model.MenuTree;

public class testClass {
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
}
