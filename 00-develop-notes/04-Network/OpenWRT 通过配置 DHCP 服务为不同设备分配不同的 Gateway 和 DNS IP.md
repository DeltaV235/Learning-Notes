---
title: OpenWRT 通过配置 DHCP 服务为不同设备分配不同的 Gateway 和 DNS IP
created: 2024-06-18
tags:
    - OpenWRT
    - Network
    - DHCP
    - Passwall
---

# OpenWRT 通过配置 DHCP 服务为不同设备分配不同的 Gateway 和 DNS IP

主路由 IP 为 `192.168.2.1`，且禁用 DHCP 服务

软路由 IP 为 `192.168.2.200`，启用 DHCP 服务，在未进行配置时所有内网设备网关默认为 `192.168.2.200`

软路由 LAN 口网关与 DNS 为 `192.168.2.1`

## 代理黑名单

只有在 `DHCP` 中配置的设备直连主路由，其他设备默认通过代理。

1. 在 `OpenWRT` 中将网络设备`MAC` 地址与 `IP` 地址静态绑定
2. 配置网络设备标签为 `direct` (标签名可以为任意字符串)
3. 进入 LAN 的 DHCP 高级配置，在 `DHCP-Options` 中添加 `tag:direct,3,192.168.2.1`
    - 为所有标签为 `direct` 的设备分配 `Gateway` 为 `192.168.2.1`
    - `tag:label` 为设备标签名
    - `3` 为 `Gateway` 选项
    - `192.168.2.1` 为 `Gateway` 地址，在此为主路由的 IP
4. 在 `DHCP-Options` 中添加 `tag:direct,6,192.168.2.1`
    - 为所有标签为 `direct` 的设备分配 `DNS` 为 `192.168.2.1`
    - `tag:label` 为设备标签名
    - `6` 为 `DNS` 选项
    - `192.168.2.1` 为 `DNS` 地址，在此为主路由的 IP
5. 保存应用

## 代理白名单

只有在 `DHCP` 中配置的设备通过代理，其他设备默认直连主路由。

1. 在 `OpenWRT` 中将网络设备`MAC` 地址与 `IP` 地址静态绑定
2. 配置网络设备标签为 `proxy` (标签名可以为任意字符串)
3. 进入 LAN 的 DHCP 高级配置，在 `DHCP-Options` 中添加 `tag:!proxy,3,192.168.2.1`
    - 为所有标签不为 `proxy` 的设备分配 `Gateway` 为 `192.168.2.1`
    - `tag:!proxy` 指标签名不为 `proxy` 的设备
    - `3` 为 `Gateway` 选项
    - `192.168.2.1` 为 `Gateway` 地址，在此为主路由的 IP
4. 在 `DHCP-Options` 中添加 `tag:!proxy,6,192.168.2.1`
    - 为所有标签不为 `proxy` 的设备分配 `DNS` 为 `192.168.2.1`
    - `tag:!proxy` 指标签名不为 `proxy` 的设备
    - `6` 为 `DNS` 选项
    - `192.168.2.1` 为 `DNS` 地址，在此为主路由的 IP
