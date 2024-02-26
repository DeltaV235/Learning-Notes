DHCP(Dynamic Host Configuration Protocol)
DHCP 服务器给接入的客户端分配 IP 地址、子网掩码、网关、DNS这些配置信息。

## 租期
一个已被分配出去的 IP 地址在租期内，无法再次被分配给其他设备。
租期可是设置为 Infinite，用于将一个 IP 永久的分配给一个设备。

## 续租


## 地址池
在地址池内的 IP 是可以被 DHCP 服务管理分配的 IP 地址集。
对于想进行静态主机配置分配的设备，可以将其静态 IP 移出动态地址池。

## DHCP 握手和网络模型

一次 DHCP 服务端与客户端的通信流程。

![[Pasted image 20230422152658.png]]

### DHCP Discover
客户端传输层是用 UDP-68 端口，服务端使用 UDP-67 端口。
网络层由于客户端未被分配 IP 地址，也未知 DHCP 服务的 IP，所以源 IP 为 `0.0.0.0`，目标 IP 为广播地址 `255.255.255.255`。
数据链路层也类似网络层，源地址为设备 MAC 地址，目标 MAC 地址为广播地址 `ff:ff:ff:ff:ff:ff`

![[Pasted image 20230422124747.png]]

### DHCP Offer

DHCP 服务提供给客户端所需要的网络配置。客户端不一定会接受改网络配置信息。

![[Pasted image 20230422131244.png]]

### DHCP Request

用于客户端确认使用的网络信息。由于网络中可能存在多个 DHCP 服务器，所以在网络层和数据链路层中，目标地址依旧是广播地址。

![[Pasted image 20230422132745.png]]

### DHCP ACK

DHCP 服务端确认客户端的 DHCP Request。发送的数据内容和 DHCP Offer 基本一致。

![[Pasted image 20230422152603.png]]