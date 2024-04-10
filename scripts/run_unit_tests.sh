#!/bin/sh
# Script shall be run from root directory
set -e

./gradlew desktopTest testDebugUnitTest jsBrowserTest

#TODO: iOS tests