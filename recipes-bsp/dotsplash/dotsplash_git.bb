SUMMARY = "./dotsplash - a tiny and customizable splash screen"
LICENSE = "GPLv2"
PV = "1.0+git${SRCPV}"
SRCREV = "eac58fd508efaa3215094085daa06a58d6d7a75c"


LIC_FILES_CHKSUM = "file://COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@gitlab.kernelconcepts.de:2224/danb/mucross_psplash.git;protocol=ssh \
          file://dotsplash-default \
          file://dotsplash-init \
          file://splashfuncs"

inherit pkgconfig update-rc.d meson


S = "${WORKDIR}/git"

FILES_${PN} += "/mnt/.dotsplash/"

PACKAGES =+ "${PN}-themes"
FILES_${PN}-themes += "${datadir}/dotsplash/themes/*"

do_install_append() {
    install -d ${D}/mnt/.dotsplash/
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/dotsplash-init ${D}${sysconfdir}/init.d/dotsplash.sh
    install -d ${D}${sysconfdir}/default/
    install -m 0644 ${WORKDIR}/dotsplash-default ${D}${sysconfdir}/default/dotsplash
    install -m 0755 ${WORKDIR}/splashfuncs ${D}${sysconfdir}/default/splashfuncs
}

INITSCRIPT_NAME = "dotsplash.sh"
INITSCRIPT_PARAMS = "start 0 S . stop 20 0 1 6 ."

RRECOMMENDS_${PN} = "${PN}-themes"
