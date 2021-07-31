#!/bin/bash

pandoc --latex-engine=xelatex -s -o review.pdf -V mainfont="WenQuanYi Zen Hei Mono" REVIEW.md

