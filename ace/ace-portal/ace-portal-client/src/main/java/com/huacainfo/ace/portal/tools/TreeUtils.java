package com.huacainfo.ace.portal.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.portal.model.Resources;

public class TreeUtils {
	
	private List<Resources> resources;
	
	private boolean loadButton;

	
	public TreeUtils( List<Resources> resources, boolean loadButton){
		this.resources=resources;
		this.loadButton=loadButton;
	}
	public  List<Tree> getTreeList(String id){
		List<Tree> list=new ArrayList<Tree>();
		List<Resources> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Resources row=(Resources)temp.get(i);
				Tree tree=this.getChildTreeList(row);
				list.add(tree);
			}
		}
		return list;
	}

	/**
	 *
	 * @param id=0;
	 * @return
	 */
	public  List<Resources> getChildResourcesList(String id){
		List<Resources> list=new ArrayList<Resources>();
		if(this.resources!=null){
			for(int i=0;i<this.resources.size();i++){
				Resources row=(Resources)this.resources.get(i);
				if(row.getParentResourcesId().equals(id)){
					if(this.loadButton){
						list.add(row);
					}else{
						if(!row.getResourcesType().equals("3")){
							list.add(row);
						}
					}
					
					
				}
			}
		}
		return list;
	}
	@SuppressWarnings("rawtypes")
	private  Tree getChildTreeList(Resources resources){
		Tree tree=new Tree();
		tree.setIcon(resources.getResourcesIcon());
		tree.setHref(resources.getResourcesUrl());
		tree.setSrc(resources.getResourcesUrl());
		tree.setId(resources.getResourcesId());
		tree.setText(resources.getResourcesName());
		if(this.loadButton){
			if(resources.getChildCount().equals("0")){
				tree.setCls("file");
				tree.setLeaf(true);
			}else{
				tree.setCls("folder");
				tree.setLeaf(false);
				List<Tree> children=new ArrayList<Tree>();
				List<Resources> list=this.getChildResourcesList(tree.getId());
				Iterator it=list.iterator();
				while(it.hasNext()){
					Resources childResources=(Resources)it.next();
					children.add(getChildTreeList(childResources));
					
				}
				tree.setChildren(children);
			}
		}else{
			if(resources.getChildCount().equals("0")||resources.getResourcesType().equals("2")){
				tree.setCls("file");
				tree.setLeaf(true);
			}else{
				tree.setCls("folder");
				tree.setLeaf(false);
				List<Tree> children=new ArrayList<Tree>();
				List<Resources> list=this.getChildResourcesList(tree.getId());
				Iterator it=list.iterator();
				while(it.hasNext()){
					Resources childResources=(Resources)it.next();
					children.add(getChildTreeList(childResources));
					
				}
				tree.setChildren(children);
			}
		}
		
		
		return tree;
	}
}
