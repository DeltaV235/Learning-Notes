---
title: Obsidian Tutorial
created: 2024-05-19
tags:
    - Tutorial
    - Obsidian
---

# Obsidian Tutorial

## Shortcuts

**Quick Switcher**: `CMD + O`
**Command Palette**: `CMD + P`

## Query

Find in current note: `CMD + F`
Search in all note: `CMD + SHIFT + F`

### Search Options 

- `keyword1 keyword2`: 搜索同时存在两个关键词的 note
- `keyword1 OR keyword2`: 搜索存在任意一个关键词的 note
- `tag:#TAG`: 搜索 TAG
- `line:(keyword1 keyword2)`: Find matches on the same line.
- `task:""`: Find all task

### Embed search results in a note

To embed search results in a note, add a `query` code block:

```query
embed OR search
```

### Reference

[Obsidian Search Plugin Help Document](https://help.obsidian.md/Plugins/Search)

## Plugins

### Dataview

#### Example

```dataview
table file.size/1024 + " KB" as file-size, file.ctime as "created time"
from "Obsidian-Related"
sort file.ctime desc
```

#### Reference

[Dataview Reference Document](https://blacksmithgu.github.io/obsidian-dataview)