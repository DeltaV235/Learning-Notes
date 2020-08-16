- WebHandler:将web.xml中的结构转换成多个Entity和Mapping对象
- WebContext:将多个Entity和mapping对象映射成Map，并提供查找url对应类名的方法
- WebApp:解析XML并生成Map，通过WebContext映射的Map，使用输入url创建对应对象的方法，并返回
- Request:对接传入的Socket输入流，解析http请求协议，将需要的参数保存为成员变量，通过方法将对应的键值对传递给有需要的方法
- Response:对接传入的Socket输出流，向输出流通过一些指定的方法写出一些数据
- Dispatcher:线程代码，传入Socket，创建Request和Response，通过Request读取请求参数，通过WebApp创建对应的Servlet，通过Servlet操作Request和Response进行与浏览器的交互

---

- LoginServlet:当url为login或g时实例化的类，后使用多态调用该servlet的指定方法
- RegisterServlet:同理
- Server:创建ServerSocket，监听指定端口，返回Socket，循环创建Dispatcher线程