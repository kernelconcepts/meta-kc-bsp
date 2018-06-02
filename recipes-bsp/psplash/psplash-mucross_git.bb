require psplash.inc
require psplash-ua.inc

PV = "0.0"
SRCREV = "b6c9d08c992f1219b8ce958b0a6fbfdc1502019a"
PR_append = "+gitr${SRCPV}"

ALTERNATIVE_PRIORITY = "40"

LIC_FILES_CHKSUM = "file://psplash.c;md5=e357d3ebf2d2a4fc0ea3cd287b411aa5"


# You can create your own pslash-hand-img.h by doing
# ./make-image-header.sh <file>.png HAND
# and rename the resulting .h to pslash-hand-img.h (for the logo)
# respectively psplash-bar-img.h (BAR) for the bar.
# You might also want to patch the colors (see patch)

SRC_URI = "git://git.labs.kernelconcepts.de/psplash.git;protocol=git;branch=master \
          file://psplash-poky-img.h \
          file://psplash-bar-img.h \
          file://psplash-default \
          file://psplash-init \
          file://splashfuncs"

S = "${WORKDIR}/git"
