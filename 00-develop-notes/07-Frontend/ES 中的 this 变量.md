---
title: ES 中的 this 变量
created: 2024-06-23
tags:
    - ES
    - ECMAScript
---

# ES 中的 this 变量

在JavaScript中，`this` 是一个重要且常用的关键词，它的值在不同的上下文中会有所不同。以下是一些常见的使用场景和规则：

1. **全局上下文**
   在全局上下文中，`this` 指向全局对象。在浏览器中，全局对象是 `window`。

   ```javascript
   console.log(this); // 在浏览器中输出: Window
   ```

2. **函数上下文**
   在普通函数中，`this` 的值取决于函数是如何被调用的。
   
   ```javascript
   function showThis() {
     console.log(this);
   }
   showThis(); // 在浏览器中输出: Window (或在严格模式下是 undefined)
   ```

3. **对象方法**
   当方法被对象调用时，`this` 指向调用该方法的对象。

   ```javascript
   const obj = {
     name: 'Alice',
     showThis: function() {
       console.log(this);
     }
   };
   obj.showThis(); // 输出: obj 对象本身
   ```

4. **构造函数**
   在构造函数中，`this` 指向新创建的实例对象。

   ```javascript
   function Person(name) {
     this.name = name;
   }
   const person = new Person('Bob');
   console.log(person.name); // 输出: Bob
   ```

5. **箭头函数**
   箭头函数没有自己的 `this` 绑定，它的 `this` 取决于外层（定义时所在的地方）的 `this`。

   ```javascript
   const obj = {
     name: 'Charlie',
     showThis: function() {
       const arrowFunc = () => {
         console.log(this);
       };
       arrowFunc(); // 输出: obj 对象本身
     }
   };
   obj.showThis();
   ```

6. **事件处理函数**
   在事件处理函数中，`this` 指向触发事件的元素。

   ```javascript
   const button = document.querySelector('button');
   button.addEventListener('click', function() {
     console.log(this); // 输出: button 元素
   });
   ```
