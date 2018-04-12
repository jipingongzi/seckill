var seckill = {
    //封装秒杀相关ajax的url
    URL : {
        now : function () {
            return '/secKill/time/now';
        },
        expose : function (killProductId) {
            return '/secKill/' + killProductId + '/expose';
        },
        execute : function (killProductId,md5) {
            return '/secKill/' + killProductId + '/' + md5 + "/execute";
        }
    },
    //详情页秒杀逻辑
    detail : {
        //详情页初始化
        init : function (params) {
            //用户手机验证和登录，计时
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var killProductId = params['killProductId'];
            if(!seckill.validPhone(killPhone)){
                //绑定手机号
                var killPhoneModel = $('#killPhoneModel');
                killPhoneModel.modal({
                    show : true,
                    backdrop : 'static',
                    keyboard : false
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if(seckill.validPhone(inputPhone)){
                        //写入cookie,7天有效期，/secKill 路径有效
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/secKill'});
                        window.location.reload();
                    }else {
                        $('#killPhoneMessage').
                        hide().
                        html('<label class="label label-danger">手机号码格式异常</label>').
                        show(300);
                    }
                });
            }
            //秒杀倒计时
            $.get(seckill.URL.now(),{},function (result) {
                if(result && result['success']){
                    var nowTime = result['data'];
                    seckill.countDown(killProductId,nowTime,startTime,endTime);
                }else {
                    console.log('result:' + result)
                }
            });
        }
    },
    validPhone : function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }
        return false;
    },
    countDown : function (killProductId,nowTime,startTime,endTime) {
        var seckillBox = $("#seckill-box");
        if(nowTime > endTime){
            seckillBox.html('秒杀结束');
        }else if(nowTime < startTime){
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime,function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown',function () {
                //时间完成后的回调
                seckill.handleSecKill(killProductId,seckillBox);
            });
        }else {
            seckill.handleSecKill(killProductId,seckillBox);
        }
    },
    handleSecKill : function (killProductId,node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.expose(killProductId),{},function (result) {
            //回调函数中执行交互
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer['exposed']){
                    var md5 = exposer['md5'];
                    var executeUrl = seckill.URL.execute(killProductId,md5);
                    console.log('executeUrl:' + executeUrl);
                    //只绑定一次点击事件
                    $('#killBtn').one('click',function () {
                        //执行秒杀:1.禁用按钮 2.发送请求
                        $(this).addClass('disable');
                        $.post(executeUrl,{},function (result) {
                            if(result && result['success']){
                                var killResult = result['data'];
                                var  status = killResult['status'];
                                var  statusInfo = killResult['statusInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-success">' + statusInfo + '</span>')
                            }else {
                                node.html('<span class="label label-success">' + result[''] + '</span>')
                            }
                        });
                    });
                    node.show();
                }else {
                    //客户端计时过快，重新倒计时
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(killProductId,now,start,end);
                }
            }else {
                console.log('result:' + result)
            }
        });
    }
};