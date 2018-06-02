DESCRIPTION = "Application launch example"
SECTION = "application"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://StartApplication.sh \
           file://logo.pnm \
           "

INITSCRIPT_NAME = "StartApplication.sh"
INITSCRIPT_PARAMS = "defaults 80"

inherit update-rc.d

do_compile () {
}

do_install () {
	install -d ${D}/etc/init.d
	install -m755 ${WORKDIR}/StartApplication.sh ${D}/etc/init.d
	install -d ${D}/opt/bitmaps
	install -m644 ${WORKDIR}/logo.pnm ${D}/opt/bitmaps/
}

FILES_${PN} += "/etc /opt/bitmaps"
