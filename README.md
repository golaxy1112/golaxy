##环境
```
jdk1.8
```
##swagger地址
``
http://localhost:7100/template/v1.0/doc.html
http://localhost:7100/template/v1.0/swagger-ui.html
``
##swagger 使用
```
@Api：修饰整个类，描述Controller的作用
 @ApiOperation：描述一个类的一个方法，或者说一个接口
 @ApiParam：单个参数描述
 @ApiModel：用对象实体来作为入参
 @ApiProperty：用对象接实体收参数时，描述对象的一个字段
 @ApiResponse：HTTP响应其中1个描述
 @ApiResponses：HTTP响应整体描述
 @ApiIgnore：使用该注解忽略这个API
 @ApiError ：发生错误返回的信息
 @ApiImplicitParam：一个请求参数
 @ApiImplicitParams： 多个请求参数
```
##性能优化
###1.尽量在合适的场合使用单例
###2.尽量避免过多过常地创建Java对象
###3.合理的创建HashMap
```
当你要创建一个比较大的hashMap时，充分利用这个构造函数
public HashMap(int initialCapacity, float loadFactor);
避免HashMap多次进行了hash重构,扩容是一件很耗费性能的事，在默认中initialCapacity只有16，而loadFactor是 0.75，需要多大的容量，你最好能准确的估计你所需要的最佳大小，同样的Hashtable，Vectors也是一样的道理。
```
###4.减少对变量的重复计算
```
for(int i=0;i<list.size();i++)
应改为：
for(int i=0,len=list.size();i<len;i++)
```
###5.尽量采用懒加载的策略，即在需要的时候才创建
```
A a = new A();
if(i==1){
    list.add(a);
}
应改为：
A a = null;
if(i==1){
    a = new A();
    list.add(a);
}
```
###6.尽量在finally块中释放资源
```
程序中使用到的资源应当被释放，以避免资源泄漏，这最好在finally块中去做。不管程序执行的结果如何，finally块总是会执行的，以确保资源的正确关闭。
如文件流等
```
###7.尽量早释放无用对象的引用
```
Public void test(){
    Object obj = new Object();
    ……
    Obj=null;
    //执行耗时，耗内存操作；或调用耗时，耗内存的方法
    ……
}
```
###8.循环内不要不断创建对象引用
```
for(int i = 1; i <= count; i++){
    Object obj = new Object;
}
// 这种做法会导致内存中有count份Object对象引用存在，count很大的话，就耗费内存了，
应改为：
Object obj = null;
for(int i = 1; i <= count; i++){
    obj = new Object;
}
// 这样的话，内存中只有一份Object对象引用，每次new Object的时候，Object对象引用指向不同的Object罢了，但是内存中只有一份，这样就大大节省了内存空间了。
```
###9.使用数据库连接池和线程池
```
这两个池都是用于重用对象的，前者可以避免频繁地打开和关闭连接，后者可以避免频繁地创建和销毁线程
```
###10.使用带缓冲的输入输出流进行IO操作
```
带缓冲的输入输出流，即BufferedReader、BufferedWriter、BufferedInputStream、BufferedOutputStream，这可以极大地提升IO效率
```
##开发规范-命名风格
###1. 【强制】代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
```
反例：_name / __name / $name / name_ / name$ / name__
```
###2. 【强制】所有编程相关的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。
```
说明：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。注意，纯拼音命名方式更要避免采用。
正例：ali / alibaba / taobao / cainiao/ aliyun/ youku / hangzhou 等国际通用的名称，可视同英文。
反例：DaZhePromotion [打折] / getPingfenByName() [评分] / int 某变量 = 3
```
###3.【强制】在 long 或者 Long 赋值时，数值后使用大写的 L，不能是小写的 l，小写容易跟数字混淆，造成误解。
```
说明：Long a = 2l; 写的是数字的 21，还是 Long 型的 2。
```
###4. 【强制】方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格。
```
正例： localValue / getHttpMessage() / inputUserId
```
###5. 【强制】常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
```
正例：MAX_STOCK_COUNT / CACHE_EXPIRED_TIME
反例：MAX_COUNT / EXPIRED_TIME
```
###6. 【强制】抽象类命名使用 Abstract 或 Base 开头；异常类命名使用 Exception 结尾；测试类命名以它要测试的类的名称开始，以 Test 结尾。
###7. 【强制】类型与中括号紧挨相连来表示数组。
```
正例：定义整形数组 int[] arrayDemo;
反例：在 main 参数中，使用 String args[]来定义。
```
###8. 【强制】POJO 类中的任何布尔类型的变量，都不要加 is 前缀，否则部分框架解析会引起序列化错误。
```
说明：在本文 MySQL 规约中的建表约定第一条，表达是与否的值采用 is_xxx 的命名方式，所以，需要在
<resultMap>设置从 is_xxx 到 xxx 的映射关系。
版本号 制定团队 更新日期 备注
1.6.0 阿里巴巴与 Java 社区开发者 2020.04.22 泰山版，首次发布错误码统一方案
Java 开发手册
2/57
反例：定义为基本数据类型 Boolean isDeleted 的属性，它的方法也是 isDeleted()，框架在反向解析的时
候，“误以为”对应的属性名称是 deleted，导致属性获取不到，进而抛出异常。
```
###9. 【强制】包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
```
正例：应用工具类包名为 com.alibaba.ei.kunlun.aap.util、类名为 MessageUtils（此规则参考 spring 的框架结构）
```
###10.【强制】避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可读性降低。
```
说明：子类、父类成员变量名相同，即使是 public 类型的变量也是能够通过编译，而局部变量在同一方法
内的不同代码块中同名也是合法的，但是要避免使用。对于非 setter/getter 的参数名称也要避免与成员变
量名称相同。
反例：
public class ConfusingName {
    public int stock;
    // 非 setter/getter 的参数名称，不允许与本类成员变量同名
    public void get(String alibaba) {
        if (condition) {
            final int money = 666;
            // ...
        }
        for (int i = 0; i < 10; i++) {
            // 在同一方法体中，不允许与其它代码块中的 money 命名相同
            final int money = 15978;
            // ...
        }
    } 
}
class Son extends ConfusingName {
    // 不允许与父类的成员变量名称相同
    public int stock; 
}
```
###11.【强制】杜绝完全不规范的缩写，避免望文不知义。
```
反例：AbstractClass“缩写”命名成 AbsClass；condition“缩写”命名成 condi，此类随意缩写严重降低了代码的可阅读性。
```
##开发规范-代码格式
###1. 【强制】如果是大括号内为空，则简洁地写成{}即可，大括号中间无需换行和空格；如果是非空代码块则： 
```
1） 左大括号前不换行。
2） 左大括号后换行。
3） 右大括号前换行。
4） 右大括号后还有 else 等代码则不换行；表示终止的右大括号后必须换行。
```
###2. 【强制】左小括号和右边相邻字符之间不出现空格；右小括号和左边相邻字符之间也不出现空格；而左大括号前需要加空格。详见第 5 条下方正例提示。
```
反例：if (空格 a == b 空格)
```
###3. 【强制】if/for/while/switch/do 等保留字与括号之间都必须加空格。
###4. 【强制】任何二目、三目运算符的左右两边都需要加一个空格。
```
说明：包括赋值运算符=、逻辑运算符&&、加减乘除符号等。
```
###5. 【强制】采用 4 个空格缩进，禁止使用 tab 字符。
```
说明：如果使用 tab 缩进，必须设置 1 个 tab 为 4 个空格。IDEA 设置 tab 为 4 个空格时，请勿勾选 Use tab 
character；而在 eclipse 中，必须勾选 insert spaces for tabs。
正例： （涉及 1-5 点）
public static void main(String[] args) {
 // 缩进 4 个空格
 String say = "hello";
 // 运算符的左右必须有一个空格
 int flag = 0;
 // 关键词 if 与括号之间必须有一个空格，括号内的 f 与左括号，0 与右括号不需要空格
 if (flag == 0) {
    System.out.println(say);
 }
 // 左大括号前加空格且不换行；左大括号后换行
 if (flag == 1) {
     System.out.println("world");
 // 右大括号前换行，右大括号后有 else，不用换行
 } else {
    System.out.println("ok");
 // 在右大括号后直接结束，则必须换行
 } 
}
```
###6. 【强制】注释的双斜线与注释内容之间有且仅有一个空格。
```
正例：
// 这是示例注释，请注意在双斜线之后有一个空格
String commentString = new String();
```
###7. 【强制】在进行类型强制转换时，右括号与强制转换值之间不需要任何空格隔开。
```
正例：
long first = 1000000000000L;
int second = (int)first + 2; 
```
###8. 【强制】单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则：
```
1）第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进，参考示例。
2）运算符与下文一起换行。
3）方法调用的点符号与下文一起换行。
4）方法调用中的多个参数需要换行时，在逗号后进行。 5）在括号前不要换行，见反例。
正例：
StringBuilder sb = new StringBuilder();
// 超过 120 个字符的情况下，换行缩进 4 个空格，并且方法前的点号一起换行
sb.append("zi").append("xin")...
 .append("huang")...
 .append("huang")...
 .append("huang");
反例：
StringBuilder sb = new StringBuilder();
// 超过 120 个字符的情况下，不要在括号前换行
sb.append("you").append("are")...append
 ("lucky");
// 参数很多的方法调用可能超过 120 个字符，逗号后才是换行处
method(args1, args2, args3, ...
 , argsX);
```
###9. 【强制】方法参数在定义和传入时，多个参数逗号后边必须加空格。
```
正例：下例中实参的 args1，后边必须要有一个空格。
method(args1, args2, args3); 
```
###10.【强制】IDE 的 text file encoding 设置为 UTF-8; IDE 中文件的换行符使用 Unix 格式，不要使用 Windows 格式。
