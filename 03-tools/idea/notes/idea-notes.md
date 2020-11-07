# IDEA使用笔记

[IntelliJ IDEA Help Documentation](https://www.jetbrains.com/help/idea)

## Plugins

1. Background Image Plus
2. CodeGlance: vscode右侧代码地图
3. Translation
4. Rainbow Brackets: 彩虹色括号
5. Grep Console: 日志着色控制台显示
6. Statistic: 代码统计
7. Markdown Navigator
8. RestfulToolkit: 快速定位controller层接口、接口测试
9. GsonFormat: Json转Java类
10. Mybatis Log Plugin: 快速打印SQL语句
11. Free Mybatis Plugin: mybatis xml id与接口间跳转

12. Material Theme UI: 美化
13. Lombok: 省掉手动set/get方法
14. Alibaba Java Coding Guidelines: 阿里巴巴开发规范
15. Easy Code: 数据库表生成JavaBean
16. JRebel for IntelliJ: JavaWeb项目热部署
17. Key Promoter X: 快捷键提示
18. .ignore: 生成git ignore文件

19. SonarLint 帮助你扫描代码的问题，例如 性能，安全和重复问题。新手必备！显著提升代码质量！
20. maven helpler 更好的进行依赖管理
21. mybatis pro 付费，但是可以有其他办法用[doge]
22. color themes 有各种好看的主题，推荐sublime 3主题

23. ANSI Highlighter 在IntelliJ编辑器下渲染ANSI转义序列
24. SequencePlugin：显示指定方法的序列图

## Shortcut

- **Window Setting**
`ctrl + shift + F12`: 隐藏所有窗口,除了编辑器
`shift + F12`: 恢复默认窗口布局
`alt + 1`: 将焦点移至project窗口,在焦点已经在project窗口时,trigger该窗口
`ctrl + e`: 最近打开的文件

---

- **Editor**
`ctrl + shift + j`: 将光标处下一行的内容合并至当前行的行尾
`ctrl + shift + up/down`: 上移/下移语句或方法
`alt + insert`: 自动创建方法或文件解决错误等功能

---

- **Surround Code & Live Template**
`ctrl + alt + t`: **Surround** with **Suggest Code Block** and **Live Template**
`ctrl + alt + j`: **Surround** with **Live Template**
`ctrl + j`: Show **All Available Live Template** and Put It on **Current Cursor**

---

- **Reformat Code**
`ctrl + alt + o`: Optimize Imports
`ctrl + alt + l`: Reformat the **whole file** or **Selected File**
`ctrl + alt + shift + l`: Show Reformat Dialog

## Default Live Template

`.var`: 声明一个变量
`.if`: 将前面的对象用if进行包裹
`iter`: 遍历最近的可遍历对象
`sout`: System.out.println()
`main/psvm`: public static void main(String[] args) {}

## Live Template Variables

- **\$END\$**
The cursor position when all parameters are filled.
`$END$` indicates the position of the cursor when the code snippet is complete, and you can no longer press Tab to jump to the next variable.
- **\$SELECTION\$**
`$SELECTION$` is used in surround templates and denotes the code fragment to be wrapped. After the template expands, it wraps the selected text as specified in the template. For example, if you select EXAMPLE in your code and invoke the "`$SELECTION$`" template via the assigned abbreviation or by pressing `Ctrl+Alt+T` and selecting the desired template from the list, IntelliJ IDEA will wrap the selection in double quotes as follows: "EXAMPLE".

[Live Template Variables Usage](https://www.jetbrains.com/help/idea/2020.2/edit-template-variables-dialog.html)

## Reformat & Rearrange

[Reformat & Rearrange Offical Documentation](https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html)

- Code cleanup
Select this option to run the code cleanup inspections. IntelliJ IDEA finds and automatically fixes potentially problematic code fragments.

## Run Commands using IDE

- *IDEA 2020 new feature*

![image-20201011234517996](idea-notes.assets/image-20201011234517996.png)

- Run Commands using IDE Press `Ctrl+Enter` to run the highlighted action using the relevant **IDE feature** instead of the terminal. Press `Ctrl+Shift+Enter` for **debug**. Press `Enter` to run the command in the terminal as **usual**. You can turn this behavior on/off in **Settings | Tools | Terminal**.

## Version Control

### Commit

- use non-modal commit interface (*IDEA 2020 new feature*)

![image-20201012011948540](idea-notes.assets/image-20201012011948540.png)
