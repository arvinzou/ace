var data = {
	"elements" : [ {
		"completed" : true,
		"current" : false,
		"id" : "startEvent1",
		"name" : "开始",
		"x" : 100.0,
		"y" : 163.0,
		"width" : 30.0,
		"height" : 30.0,
		"type" : "StartEvent",
		"properties" : []
	}, {
		"completed" : false,
		"current" : false,
		"id" : "sid-E448E868-E367-491E-996D-9A5F9B1EA8A4",
		"name" : "结束",
		"x" : 585.0,
		"y" : 162.0,
		"width" : 28.0,
		"height" : 28.0,
		"type" : "EndEvent",
		"properties" : []
	}, {
		"completed" : false,
		"current" : true,
		"id" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"name" : "组长审批",
		"x" : 210.0,
		"y" : 195.0,
		"width" : 100.0,
		"height" : 80.0,
		"type" : "UserTask",
		"properties" : [ {
			"name" : "Assignee",
			"value" : "admin"
		}, {
			"name" : "Form key",
			"value" : "201703090002"
		} ]
	}, {
		"completed" : false,
		"current" : false,
		"id" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"name" : "经理审批",
		"x" : 372.0,
		"y" : 220.0,
		"width" : 100.0,
		"height" : 80.0,
		"type" : "UserTask",
		"properties" : [ {
			"name" : "Assignee",
			"value" : "admin"
		}, {
			"name" : "Form key",
			"value" : "201703090003"
		} ]
	} ],
	"flows" : [ {
		"completed" : true,
		"current" : false,
		"id" : "sid-13A028D6-5CEA-4755-BB59-5E099CC0BF4F",
		"type" : "sequenceFlow",
		"sourceRef" : "startEvent1",
		"targetRef" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"waypoints" : [ {
			"x" : 129.0,
			"y" : 183.0
		}, {
			"x" : 210.0,
			"y" : 215.0
		} ],
		"properties" : []
	}, {
		"completed" : false,
		"current" : false,
		"id" : "sid-D2CC7539-A2B9-4FF0-97B8-12C267EC24B5",
		"type" : "sequenceFlow",
		"sourceRef" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"targetRef" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"waypoints" : [ {
			"x" : 310.0,
			"y" : 243.0
		}, {
			"x" : 372.0,
			"y" : 252.0
		} ],
		"properties" : []
	}, {
		"completed" : false,
		"current" : false,
		"id" : "sid-62669791-846F-410B-B666-7DC0C237A0C2",
		"type" : "sequenceFlow",
		"sourceRef" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"targetRef" : "sid-E448E868-E367-491E-996D-9A5F9B1EA8A4",
		"waypoints" : [ {
			"x" : 472.0,
			"y" : 236.0
		}, {
			"x" : 586.0,
			"y" : 182.0
		} ],
		"properties" : []
	} ],
	"diagramBeginX" : 115.0,
	"diagramBeginY" : 162.0,
	"diagramWidth" : 613.0,
	"diagramHeight" : 300.0,
	"completedActivities" : [ "startEvent1" ],
	"currentActivities" : [ "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7" ],
	"completedSequenceFlows" : [ "sid-13A028D6-5CEA-4755-BB59-5E099CC0BF4F" ]
};
var data2 = {
	"diagramBeginY" : 162.0,
	"diagramBeginX" : 115.0,
	"flows" : [ {
		"targetRef" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"current" : false,
		"completed" : true,
		"id" : "sid-13A028D6-5CEA-4755-BB59-5E099CC0BF4F",
		"type" : "sequenceFlow",
		"sourceRef" : "startEvent1",
		"waypoints" : [ {
			"x" : 128.0,
			"y" : 183.0
		}, {
			"x" : 210.0,
			"y" : 215.0
		} ],
		"properties" : []
	}, {
		"targetRef" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"current" : false,
		"completed" : false,
		"id" : "sid-D2CC7539-A2B9-4FF0-97B8-12C267EC24B5",
		"type" : "sequenceFlow",
		"sourceRef" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"waypoints" : [ {
			"x" : 310.0,
			"y" : 242.0
		}, {
			"x" : 372.0,
			"y" : 252.0
		} ],
		"properties" : []
	}, {
		"targetRef" : "sid-E448E868-E367-491E-996D-9A5F9B1EA8A4",
		"current" : false,
		"completed" : false,
		"id" : "sid-62669791-846F-410B-B666-7DC0C237A0C2",
		"type" : "sequenceFlow",
		"sourceRef" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"waypoints" : [ {
			"x" : 472.0,
			"y" : 236.0
		}, {
			"x" : 586.0,
			"y" : 182.0
		} ],
		"properties" : []
	} ],
	"diagramHeight" : 300.0,
	"elements" : [ {
		"current" : false,
		"name" : "开始",
		"x" : 100.0,
		"width" : 30.0,
		"y" : 163.0,
		"completed" : true,
		"id" : "startEvent1",
		"type" : "StartEvent",
		"properties" : [],
		"height" : 30.0
	}, {
		"current" : false,
		"name" : "结束",
		"x" : 585.0,
		"width" : 28.0,
		"y" : 162.0,
		"completed" : false,
		"id" : "sid-E448E868-E367-491E-996D-9A5F9B1EA8A4",
		"type" : "EndEvent",
		"properties" : [],
		"height" : 28.0
	}, {
		"current" : true,
		"name" : "组长审批",
		"x" : 210.0,
		"width" : 100.0,
		"y" : 195.0,
		"completed" : false,
		"id" : "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7",
		"type" : "UserTask",
		"properties" : [ {
			"name" : "Assignee",
			"value" : "admin"
		}, {
			"name" : "Form key",
			"value" : "201703090002"
		} ],
		"height" : 80.0
	}, {
		"current" : false,
		"name" : "经理审批",
		"x" : 372.0,
		"width" : 100.0,
		"y" : 220.0,
		"completed" : false,
		"id" : "sid-513D5728-3861-4731-BF76-A68C73C982F0",
		"type" : "UserTask",
		"properties" : [ {
			"name" : "Assignee",
			"value" : "admin"
		}, {
			"name" : "Form key",
			"value" : "201703090003"
		} ],
		"height" : 80.0
	} ],
	"completedSequenceFlows" : [ "sid-13A028D6-5CEA-4755-BB59-5E099CC0BF4F" ],
	"completedActivities" : [ "startEvent1" ],
	"diagramWidth" : 613.0,
	"currentActivities" : [ "sid-E99F8D70-F347-4221-BAA7-0F2DEF67FBD7" ]
};