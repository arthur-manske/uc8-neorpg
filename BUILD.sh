#!/bin/sh
cont="build"

gradle build
rm -rf -- "$cont"

unzip -d"$cont" -- app/build/distributions/app.zip
