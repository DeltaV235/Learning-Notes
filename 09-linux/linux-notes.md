# Linux笔记

## command line

## alias

## shell/bash

## cd/rm/cp/mv/less/more/cat/tail/od

## authoritication

## user:group:other

## vim

## .bashrc

## dir

## rpm

## yum

## ssh

## ^d/^l/^c/^z/^u/^w/^r/

## pipe

## du/df/fileSystem

## mount

## tar/gzip/gunzip

## scp/ftp

## systemctl/service

## reg

## export/env variable

## su -

## root

## shell script

## std/serr/sin

## redirect

## grep

## deploy

## ssh秘钥登录配置

```bash
cd ~/.ssh
cat id_rsa >> authorized_keys

sudo vim /etc/ssh/sshd_config

# 使用公钥授权登录
PubkeyAuthentication yes

# 是否允许以root身份ssh，no为不允许
PermitRootLogin no
```

**.ssh目录权限必须为700，authorized_keys权限为600，user group 为登录用户** !

## misc

### 多人共享命令行 IO

**Cation**: need root permission

```bash
sudo screen -S foo # 指定screen作业的名称
sudo screen -x foo # 恢复之前离线的screen作业
```
