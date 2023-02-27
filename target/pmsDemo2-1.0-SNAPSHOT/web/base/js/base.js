
function toHref(url){
    location.href=base.app + url;
}

var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var laytpl = layui.laytpl;
var laypage = layui.laypage;
var upload = layui.upload;
var laydate = layui.laydate;

function formOnSubmit(event,url,dataType,func) {
    form.on("submit("+event+")",function(data) {
        ajax(url, data.field,dataType, func)
    })
}

function ajax(url,field,dataType,func){
    $.ajax({
        url: base.app + url,
        data: field,//a=b&c=d / {a:b,c:d}
        type: "post",
        dataType: dataType,//text / json
        success: func
    });
}

function layerConfirm(func,title){
    layer.confirm(title ? title:" 确定进行该操作?" , {
        icon : 3,
        title : "警告"
    },func)
}

function layerOpen(url,end){
    layer.open({
        type: 2,
        content : base.app + url,
        area : ['500px','400px'],
        offset: '240px',
        fixed: true,
        shade : 0.3,
        shadeClose : true,
        success: function(layero,index){},
        end : end
    });
}

function layerClose() {
    //当你在iframe页面关闭自身时
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}
function refresh() {
    // $("input[type='reset']").click();
    $("input[value='查询']").click();
}
function pageInfo(elem,count,curr,limit,jump) {
    laypage.render({
        elem : elem,
        count : count,
        theme: '#1E9FFF',
        curr : curr,
        limit : limit,
        limits : [ 10 ,20 ,30 ,40 ,50 ],
        layout : ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
        jump : jump,
    })
}

function renderUpload(id,url,data,done) {//将按钮渲染成可进行上传操作的
    var upload = layui.upload;

    //执行实例
    var uploadInst = upload.render({
        elem: '#' + id //绑定元素
        ,url: base.app+url //上传接口
        ,data: data//非文件域参数(上传过程中额外的参数)
        ,done: done//上传完毕回调
    });
}

function renderLaydate(id,type) {
    laydate.render({
        elem : "#" + id,
        type : type ? type : "date"
    });

}

function renderLaydateTime(id) {
    renderLaydate(id,'datetime')
}