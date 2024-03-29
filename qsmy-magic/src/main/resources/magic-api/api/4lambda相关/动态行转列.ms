{
  "properties" : { },
  "id" : "679ace6a76b1435b8a1e75bca28344d7",
  "script" : null,
  "groupId" : "aa81e76ddfd7471a97aa423abd80b2aa",
  "name" : "动态行转列",
  "createTime" : null,
  "updateTime" : 1632192529131,
  "lock" : null,
  "method" : "GET",
  "path" : "/pivot",
  "parameters" : [ ],
  "option" : "[]",
  "requestBody" : "",
  "headers" : [ ],
  "paths" : [ ],
  "responseBody" : null,
  "description" : null,
  "requestBodyDefinition" : null,
  "responseBodyDefinition" : null,
  "optionMap" : { }
}
================================
var result = db.select('select api_method,api_group_id from magic_api_info');
// 期待结果如下
/*
  -     GET    POST 
分组1    X       X
分组2    X       X
分组3    X       X
分组4    X       X
其中X为该分组对应请求方法的数量
*/
return result.group(it => it.api_group_id,    // 根据api_group_id 分组
    list => list.group(item => item.api_method, // 根据 请求方法 再次分组
        apis => apis.size()
    )
).asList((key,value)=>{ // 将分组后的Map<String,Map<String,Object>> 结果转为List<Map<String,Object>
    ...value,   // 展开Map
    group_id : key
})
//关联分组表,添加分组中文名，此步骤也可以放在SQL中关联。
.join(db.select('select id,group_name from magic_group'),(t1,t2) => t1.group_id == t2.id)  
// 移除多余的ID列
.each(it => it.remove('id')) 