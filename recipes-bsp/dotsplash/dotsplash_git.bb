SUMMARY = "./dotsplash - a tiny and customizable splash screen"
LICENSE = "GPLv2"
PV = "1.0+git${SRCPV}"
SRCREV = "3b6c0abd70aadc2ceb60ec523957002de9dba5bb"


LIC_FILES_CHKSUM = "file://COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@gitlab.kernelconcepts.de:2224/danb/dotsplash.git;protocol=ssh \
          file://dotsplash-default \
          file://dotsplash-init \
          file://splashfuncs"

inherit pkgconfig update-rc.d meson


S = "${WORKDIR}/git"

FILES_${PN} += "/mnt/.psplash/"

do_install_append() {
    install -d ${D}/mnt/.psplash/
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/dotsplash-init ${D}${sysconfdir}/init.d/dotsplash.sh
    install -d ${D}${sysconfdir}/default/
    install -m 0644 ${WORKDIR}/dotsplash-default ${D}${sysconfdir}/default/dotsplash
    install -m 0755 ${WORKDIR}/splashfuncs ${D}${sysconfdir}/default/splashfuncs
    cd ${D}/${bindir}
    ln -s dotsplash-write psplash-write
}

INITSCRIPT_NAME = "dotsplash.sh"
INITSCRIPT_PARAMS = "start 0 S . stop 20 0 1 6 ."

RRECOMMENDS_${PN} = "${PN}-theme-mucross"

python populate_packages_prepend () {
    themedir = d.expand('${datadir}/dotsplash/themes')
    do_split_packages(d, themedir, '^(.*)$',
                     'dotsplash-theme-%s', './dotsplash theme %s',
                      extra_depends='', allow_dirs=True, prepend=True)
}

PACKAGES_DYNAMIC += "^dotsplash-theme-.*"
            