---
title: Array
created: 2024-06-29
tags:
    - ES
    - ECMAScript
---

# Array

## Reference

[ES5-Tutorial](https://wangdoc.com/javascript/types/array)

## 数组的本质

数组是一种特殊的对象，其键名是按次序排列的一组整数（0，1，2...）。

```js
var arr = ['a', 'b', 'c'];

Object.keys(arr)
// ["0", "1", "2"]
```

上面代码中，`Object.keys`方法返回数组的所有键名。可以看到数组的键名就是整数0、1、2。

## 数组的读取

对象有两种读取成员的方法：点结构（`object.key`）和方括号结构（`object[key]`）。但是，对于数值的键名，不能使用点结构。

```js
var arr = [1, 2, 3];
arr.0 // SyntaxError
```

上面代码中，`arr.0`的写法不合法，因为单独的数值不能作为标识符（identifier）。所以，数组成员只能用方括号`arr[0]`表示（方括号是运算符，可以接受数值）。

## length

数组的本质决定了它的键名是整数，同时还有一个`length`属性，返回数组的成员数量。

```js
var arr = ['a', 'b', 'c'];
arr.length // 3
```

`length`属性的值是一个比数组成员个数大的整数，它比最大的那个键名大1。

```js
var arr = ['a', 'b', 'c'];
arr[5] = 'e';
arr.length // 6
```

上面代码中，数组`arr`的长度是6，这是因为最大的键名是5，所以`length`属性等于6。

如果人为设置`length`属性为一个小于当前成员个数的值，该数组的成员会自动减少到`length`属性设定的值。

```js
var arr = ['a', 'b', 'c'];
arr.length = 2;
arr // ['a', 'b']
```

上面代码中，`length`属性设为2，所以数组`arr`的成员只有两个，第三个成员`'c'`自动消失。

如果`length`属性设为大于当前成员个数的值，该数组的成员会自动增加到`length`属性设定的值，新增的位置都是空位（`empty`）。

```js
var arr = ['a', 'b', 'c'];
arr.length = 5;
arr // ['a', 'b', 'c', empty × 2]
```

上面代码中，`length`属性设为5，所以数组`arr`的成员数量增加到5，新增的位置都是空位。

## 空位

数组的某一个位置没有任何值，称为“空位”（`empty`）。空位不是`undefined`，一个位置的值等于`undefined`依然有值，空位是没有任何值。空位是数组的一个属性，不是数组的一个元素。

```js
var arr = ['a', 'b', 'c'];
delete arr[1];
arr // ['a', empty, 'c']
```

上面代码中，`arr`是一个拥有3个元素的数组，删除`arr[1]`后，`arr`变成了一个稀疏数组，`arr[1]`是一个空位。

数组的空位是可以读取的，返回`undefined`。

```js
var arr = [, ,];
arr // [undefined × 2]
```

上面代码中，数组`arr`有两个空位，`arr[0]`和`arr[1]`都是`undefined`。

空位不会影响`length`属性。

```js
var a = [1, , ,];
a.length // 3
```

上面代码中，数组`a`的`length`属性为3，因为它有两个空位。

需要注意的是，如果最后一个元素后面有逗号，并不会产生空位。也就是说，有没有这个逗号，结果都是一样的。

```js
var a = [1, 2, 3,];

a.length // 3
a // [1, 2, 3]
```

上面代码中，`a`的`length`属性是3，而不是4，因为最后一个元素后面的逗号被忽略了。

## 稀疏数组

如果数组的某一个位置是空位，`forEach()`、`for...in`循环、`Object.keys()`都会跳过这个位置。但是，`undefined`不会被跳过。

```js
var a = [,,];
a.forEach(function (x, i) { console.log(i + '. ' + x) });
// noting is output
```

上面代码中，数组`a`有两个空位，`forEach()`方法没有输出任何结果。

```js
var a = new Array(3);
a[0] = 'a';
Object.keys(a) // ['0']
```

上面代码中，数组`a`有三个空位，但是只有一个键名`0`。
