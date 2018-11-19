#! /bin/bash -
if [[ ! -e Echanges/$1 ]]; then
    mkdir -p /Echanges
    touch Echanges/$1
fi
