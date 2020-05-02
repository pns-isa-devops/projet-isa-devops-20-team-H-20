#!/bin/bash

# the image will be removed when stopped
docker run --rm -it -v `pwd`:/host livrair/dd-client-boss_cli

# ^C to stop
