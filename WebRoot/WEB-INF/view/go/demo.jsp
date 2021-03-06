<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>Score.js Sample & Documents</title>

    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/font-awesome-3.2.1.css'>
    <link rel="stylesheet" href="${BASE_PATH}/plug/score/css/prism.css">
    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/jQuery.score.css'>
    <link rel='stylesheet' href='${BASE_PATH}/plug/score/css/style.css'>

    <script src="${BASE_PATH}/plug/score/js/jquery-1.11.1.js"></script>
    <script src="${BASE_PATH}/plug/score/js/prism.js"></script>
    <script src="${BASE_PATH}/plug/score/js/jQuery.score.js"></script>
    <script src="${BASE_PATH}/plug/score/js/application.js"></script>
</head>

<body>
    <div id="container">
        <header>
            <div class="logo">
                <h1>Score.js -  Sample & Documents</h1>
                <p>jQuery Star Rating Plugin</p>
            </div>
        </header>
            
        <div class="section">
            <h1>Option</h1>
            <div class="part">
                <h2>Default</h2>
                <hr>
                <p>使用默认参数启用scorejs，当目标标签为 'ul' 时使用将 'li' 标签初始化，否则使用 'span' 标签初始化</p>
                <div class="example">
                    <div style="float:left; width:512px;">
                        <span>div</span>
                        <div class="demo-option-default"></div>
                    </div>
                    <div class="divider-vertical" style="float:left;"></div>
                    <div style="float:left;">
                        <span>ul</span>
                        <ul class="demo-option-default"></ul>
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <div class="code">
                    <pre><code class="language-markup">&lt;div class=&quot;demo-default&quot;&gt;&lt;/div&gt; Or &lt;ul class=&quot;demo-default&quot;&gt;&lt;/ul&gt;</code></pre>
                    <pre><code class="language-javascript">$('.demo-default').score();</code></pre>
                </div>
                <p>配置项默认值保存在$.fn.score.defaults，可在使用前直接修改，修改后将影响到之后创建的所有实例</p>
                <p>插件配置项默认值如下：</p>
                <div class="code">
                    <pre><code class="language-javascript">$.fn.score.defaults = {
    number      : 5,
    size        : 26,
    color       : 'black',
    score       : undefined,
    vertical    : false,
    hints       : undefined,
    click       : undefined,
    mouseover   : undefined,
    mouseout    : undefined,
    readOnly    : false,
    fontAwesome : false,
    debug       : false
}</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Number</h2>
                <hr>
                <p>评分的范围，设定的数字代表可用来评分的图标总数</p>
                <div class="example">
                    <ul class="demo-option-number"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-number').score({
    number: 10
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Size</h2>
                <hr>
                <p>图标的大小，按照CSS的文字尺寸单位px控制大小</p>
                <div class="example">
                    <ul class="demo-option-size"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-number').score({
    size: 32
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Color</h2>
                <hr>
                <p>图标的颜色，按照CSS的颜色规则取值</p>
                <div class="example">
                    <ul class="demo-option-color"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-color').score({
    color: '#08C'
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Score</h2>
                <hr>
                <p>初始化时的分数</p>
                <div class="example">
                    <ul class="demo-option-score"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-score').score({
    score: 3
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Vertical</h2>
                <hr>
                <p>垂直模式，即竖向排列图标</p>
                <div class="example">
                    <ul class="demo-option-vertical"></ul>
                </div>
                <p>提示：为了能够更自由的在页面定义样式，插件不强行限制图标的宽度，由于垂直分布需要使用块级元素，因此需要自己定义浮动以及清理来自适应宽度</p>
                <p>自适应宽度示例(自行查看源代码参考)：</p>
                <div class="example">
                    <ul class="demo-option-vertical" style="float:left;"></ul>
                    <div style="clear:both;"></div>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-vertical').score({
    vertical: true
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Hints</h2>
                <hr>
                <p>用指定文本替换评分条目提示，例: ['bad', 'poor', 'regular', 'good', 'gorgeous']（默认是从1开始的阿拉伯数字）</p>
                <p>注意：如果给定的数组元素多余图标数将省略后面的，反之缺少的以对应的阿拉伯数字补上</p>
                <div class="example">
                    <ul class="demo-option-hints"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-hints').score({
    hints: ['bad', 'poor', 'regular', 'good', 'gorgeous']
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Click</h2>
                <hr>
                <p>评分图标Click事件回调函数</p>
                <p>回调函数中的this指向</p>
                <div class="example">
                    <ul class="demo-option-click"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-click').score({
    click: function(score, event){
        alert('Class Name: '+this.className+'\n' + 'Score: '+score+'\n' + 'Event Type: '+event.type+'\n');
    }
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Mouseover</h2>
                <hr>
                <p>评分图标Mouseover事件回调函数</p>
                <div class="example">
                    <ul class="demo-option-mouseover"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-mouseover').score({
    mouseover: function(score, event){
        alert('Class Name: '+this.className+'\n' + 'Score: '+score+'\n' + 'Event Type: '+event.type+'\n');
    }
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Mouseout</h2>
                <hr>
                <p>评分图标Mouseout事件回调函数</p>
                <div class="example">
                    <ul class="demo-option-mouseout"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-mouseout').score({
    mouseout: function(score, event){
        alert('Class Name: '+this.className+'\n' + 'Score: '+score+'\n' + 'Event Type: '+event.type+'\n');
    }
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Read Only</h2>
                <hr>
                <p>图标的大小，按照CSS的文字尺寸单位px控制大小</p>
                <div class="example">
                    <ul class="demo-option-readOnly"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-readOnly').score({
    readOnly: true,
    score: 3
});</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>FontAwesome</h2>
                <hr>
                <p>图标的大小，按照CSS的文字尺寸单位px控制大小</p>
                <div class="example">
                    <ul class="demo-option-fontAwesome"></ul>
                </div>
                <div class="code">
                    <pre><code class="language-javascript">$('.demo-option-fontAwesome').score({
    fontAwesome: true
});</code></pre>
                </div>
            </div>
        </div>
        
        
        <div class="section">
            <h1>API</h1>
            <div class="part">
                <h2>Score</h2>
                <hr>
                <p>手动设置分数，如果省略第二个参数将返回当前的分数</p>
                <p>设置分数作用于当前jQuery选择器选定的所有元素，获取分数只作用于jQuery对象集合中的第一个元素</p>
                <p>注意：如果设定的分数超过Number大小将设置为满分</p>
                <div class="code">
                    <pre><code class="language-javascript">$('.score').score('score', score); // 设置分数为score</code></pre>
                    <pre><code class="language-javascript">$('.score').score('score'); // 获取分数</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Option</h2>
                <hr>
                <p>修改或增加配置，如果省略第二个参数将返回当前的配置项对象</p>
                <p>给定的配置项同样是对象的形式，其行为是将参数给定的配置项与当前的配置项合并后重新加载</p>
                <p>设置配置项作用于当前jQuery选择器选定的所有元素，获取配置项只作用于jQuery对象集合中的第一个元素</p>
                <div class="code">
                    <pre><code class="language-javascript">$('.score').score('option', {optionKey:optionValue[,optionKey:optionValue]}); // 设置配置项</code></pre>
                    <pre><code class="language-javascript">$('.score').score('option'); // 获取配置项</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>ReadOnly</h2>
                <hr>
                <p>设置为只读模式</p>
                <p>此方法仅仅改变展示方式，并不会修改配置项</p>
                <p>提示：此方法被触发时将移除所有已绑定的事件，并显示最近设置的分数，如果触发前鼠标仍然在图标上移动但未点击则会立刻显示为前一次点击时的分数</p>
                <div class="code">
                    <pre><code class="language-javascript">$('.score').score('readOnly', true); // 设置为只读</code></pre>
                    <pre><code class="language-javascript">$('.score').score('readOnly', false); // 取消只读</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Cancel</h2>
                <hr>
                <p>取消当前评分</p>
                <div class="code">
                    <pre><code class="language-javascript">$('.score').score('cancel');</code></pre>
                </div>
            </div>
            <div class="part">
                <h2>Destroy</h2>
                <hr>
                <p>销毁插件实例</p>
                <p>提示：销毁实例时，在初始化前设置的style属性将被还原，初始化之后手动增加和修改的style属性值将被保留（参考Live Demo）</p>
                <div class="code">
                    <pre><code class="language-javascript">$('.score').score('destroy');</code></pre>
                </div>
            </div>
        </div>
        
        
        <div class="section">
            <h1>Live Demo</h1>
            <div class="example" style="margin-bottom: 20px;">
                <ul class="demo-live-application" style="color:red; font-size:10px;"></ul>
            </div>
            <div class="code">
                <pre><code class="language-javascript">$('.demo-live-application').score({
    number      : 10,
    size        : 32,
    color       : '#08C',
    score       : 4,
    vertical    : false,
    hints       : ['bad', 'poor', 'regular', 'good', 'gorgeous'],
    click       : function(score, event){
        alert('Class Name: '+this.className+'\n' + 'Score: '+score+'\n' + 'Event Type: '+event.type+'\n');
    },
    readOnly    : false,
    fontAwesome : true,
    debug       : true
});
</code></pre>
            </div>
            <div class="part">
                <div class="live-part" style="float:left; width:500px;">
                    <div>
                        <span>Set Score</span>
                        <input class="api-score-input" type="text" value="1">
                        <button class="api-score-set">Run</button>
                    </div>
                    <div>
                        <span>Get Score</span>
                        <button class="api-score-get">Run</button>
                    </div>
                    <div>
                        <span>Set Option</span>
                        <input class="api-option-input" type="text" value="{number:5,size:40,color:'gold'}" style="width:300px;">
                        <button class="api-option-set">Run</button>
                    </div>
                    <div>
                        <span>Get Option</span>
                        <button class="api-option-get">Run</button>
                    </div>
                    <div>
                        <span>Set ReadOnly</span>
                        <button class="api-readOnly-true">Run</button>
                    </div>
                </div>
                <div class="live-part" style="float:right; width:500px;">
                    <div>
                        <span>Set ReadOnly with 5s delay</span>
                        <button class="api-readOnly-true-5s">Run</button>
                    </div>
                    <div>
                        <span>Unset ReadOnly</span>
                        <button class="api-readOnly-false">Run</button>
                    </div>
                    <div>
                        <span>Cancel</span>
                        <button class="api-cancel">Run</button>
                    </div>
                    <div>
                        <span>Destroy</span>
                        <button class="api-destroy">Run</button>
                    </div>
                    <div>
                        <span>以例子给定的配置再次初始化</span>
                        <button class="live-application-create">Run</button>
                    </div>
                </div>
                <div style="clear:both;"></div>
            </div>
            <p>说明：以上代码实现请自行查看application.js</p>
        </div>
    </div>
</body>
</html>