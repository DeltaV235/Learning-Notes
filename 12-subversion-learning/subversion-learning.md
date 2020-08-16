# subversion笔记

## 1. 安装

```shell
sudo apt install subversion
```

```shell
sudo yum install subversion
```

## 创建版本库

```shell
svnadmin create repoPath
```

## 启动svn服务器

```shell
svnserve -d -r repoRootPath
         -d 表示后台执行 daemon
         -r 表示版本库的根目录 root
```

### 查看服务是否启动成功

```shell
netstat -an | grep 3690
```

## 权限控制

### 1.修改仓库下的conf/svnserve.conf

```shell
# 启用
auth-access = write
password-db = passwd
authz-db = authz
```

### 2.配置passwd文件

```shell
wuyue = testpass
test = test
```

### 3.配置authz文件

```shell
[groups]
# 指定用户组
dev = wuyue,kaifa

[/]
# 开发用户组拥有读写权限
@dev = rw
# test用户拥有读权限
test = r
# 其余用户没有任何权限
* =
```

## SVN主要命令

### add

```shell
svn add fileName
# 将指定的文件纳入svn的版本控制中
```

### checkout

```shell
svn checkout repoName
# 将远程仓库中的文件检出到当前目录下
```

### commit

```shell
svn commit [-m "commit message"]
# 向远端仓库提交更改,若不使用-m选项,则调用本地的文本编辑器输入提交信息
```

### update

```shell
svn update [fileName]
# 将远端仓库中的更新同步至本地
```
