---
title: LF will be replaced by CRLF the next time Git touches it
created: 2024-05-22
tags:
    - Git
    - Tool
    - Cross-Platform
---

# LF will be replaced by CRLF the next time Git touches it

"LF will be replaced by CRLF the next time Git touches it" 这个警告的出现是因为 Windows 和 Unix 系统的换行符不同。Windows 使用的是 CRLF（Carriage Return Line Feed，即回车换行），而 Unix 系统使用的是 LF（Line Feed，即换行）。

当你在 Windows 系统上编辑文件时，文件的换行符是 CRLF。但是当你将文件提交到 Git 仓库时，Git 会将换行符转换为 LF。这样就会导致文件的换行符发生变化，从而出现这个警告。

这个转换是由 Git 的一个配置项 core.autocrlf 控制的。如果你不希望 Git 进行这种自动转换，可以通过设置这个配置项来关闭它。
