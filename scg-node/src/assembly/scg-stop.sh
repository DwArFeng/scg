#!/bin/sh
# 程序的根目录
basedir=/usr/local/scg

PID=$(cat "$basedir/scg.pid")
kill "$PID"
