# Linux笔记

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
