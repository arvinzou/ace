package com.huacainfo.ace.common.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huacainfo.ace.common.model.view.Tree;

public class CommonTreeUtils {


    private List<Map<String,Object>> resources;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public CommonTreeUtils(List<Map<String,Object>> resources){
		this.resources=resources;
	}
	public  List<Tree> getTreeList(String id){
		List<Tree> list=new ArrayList<Tree>();
        List<Map<String, Object>> temp = this.getChildResourcesList(id);
        if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,Object> row=temp.get(i);
				Tree tree=this.getChildTreeList(row);
				list.add(tree);
			}
		}
		return list;
	}
	public  List<Tree> getTreeListCaseSelf(String id){
		logger.info("getTreeListCaseSelf->",id);
		Map<String,Object> resources =null;
		for(Map<String,Object> temp:this.resources){
			if(String.valueOf(temp.get("ID")).equals(id)){
				resources=temp;
				break;
			}
		}
		Tree o=this.getChildTreeList(resources);

		List<Tree> list=new ArrayList<Tree>();
        List<Map<String, Object>> temp = this.getChildResourcesList(id);
        if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,Object> row=temp.get(i);
				Tree tree=this.getChildTreeList(row);
				list.add(tree);
			}
		}
		o.setChildren(list);
		List<Tree> rst=new ArrayList<Tree>();
		rst.add(o);
		return rst;
	}
	public  List<Map<String,Object>> getChildResourcesList(String id){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		if(this.resources!=null){
			for(int i=0;i<this.resources.size();i++){
				Map<String,Object> row=(Map<String,Object>)this.resources.get(i);
				if(String.valueOf(row.get("PID")).equals(id)){
                    list.add(row);
                }
			}
		}
		return list;
	}
	private  Tree getChildTreeList(Map<String,Object> resources){
		Tree tree=new Tree();
		tree.setIcon(String.valueOf(resources.get("ICON")));
		tree.setHref(String.valueOf(resources.get("HREF")));
		tree.setSrc(String.valueOf(resources.get("SRC")));
		tree.setId(String.valueOf(resources.get("ID")));
		tree.setText(String.valueOf(resources.get("TEXT")));
		tree.setIconCls(String.valueOf(resources.get("ICONCLS")));
		if(resources.get("STATE")==null){
			tree.setState("open");
		}else{
			tree.setState(String.valueOf(resources.get("STATE")));
		}
		if(String.valueOf(resources.get("CHILD_COUNT")).equals("0")){
			tree.setCls("file");
			tree.setLeaf(true);
			tree.setState("open");
		}else{
			tree.setCls("folder");
			tree.setLeaf(false);
			List<Tree> children=new ArrayList<Tree>();
			List<Map<String,Object>> list=this.getChildResourcesList(tree.getId());
			for(Map<String,Object> childResources:list){
				children.add(getChildTreeList(childResources));

            }
			tree.setChildren(children);
		}
		return tree;
	}
}