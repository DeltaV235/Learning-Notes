#!/bin/bash
# Program:
# 	show how many line the git repo has.
# History:
#	DeltaV235	2019/08/23	V1.0

if [ "$1" == '' ];then
	username="$(git config --global user.name)"
else
	username="$1"
fi
git log --author="$username" --pretty=tformat: --numstat | awk '{ add += $1; subs += $2; loc += $1 - $2 } END { printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }' -
