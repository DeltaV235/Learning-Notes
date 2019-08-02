## 1.git基础
### git常用命令

* git 的配置
```shell
git config --list --local       # 查看版本库范围的所有设置(若不指定local，则显示所有范围的设置)
git config --list --global      # 查看global范围的设置参数
git config --list --system      # 查看system范围的设置
git config --list user.name     # 只显示user.name的值
git config --local user.name 'username'     # 修改local(版本库)范围的user.name(若不加变量作用域，则默认为local)
git config --global user.email 'email@domain'   # 修改global范围的user.email3
git config --unset varname      # 删除变量
git config --local --edit       # 编辑各域的配置文件(默认为local)
```
---

```shell
git init                        # 初始化版本库
git add filename                # 添加文件至暂存区
git add -u                      # 添加已tracked并修改的文件至暂存区
git add -A                      # 将当前工作区的所有文件add至暂存区
git commit -m 'commit message'  # 提交当前暂存区至历史版本库
```
---
```shell
git log --all --oneline --gragh # 查看版本库的提交历史 
# --all:查看所有分支的提交历史，默认为当前分支 
# --oneline:所有提交历史显示成一行 
# --gragh:图像化的显示提交的历史，若有all选项，则会显示所有分支的演进过程
git log branchname              # 查看指定分支的提交历史，与--all一起使用则无效
```
---
#### .git目录
* HEAD : 当前的分支(refs/heads/master)
* config : local的配置文件(当前版本库)