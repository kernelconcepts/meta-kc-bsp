SUMMARY = "Runs custom scripts on first boot of the target device"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
	file://firststart.init \
	file://firststart.sh \
	file://banner.sh \
"

S = "${WORKDIR}"

inherit allarch update-rc.d

INITSCRIPT_NAME = "firststart"
INITSCRIPT_PARAMS = "start 99 S ."

do_configure() {
	:
}

do_compile () {
	:
}

do_install() {
	install -d ${D}${sysconfdir}/firststart.d/
	install -m 0755 ${WORKDIR}/banner.sh ${D}/${sysconfdir}/firststart.d/01banner
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/firststart.init ${D}${sysconfdir}/init.d/firststart
	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/firststart.sh ${D}${sbindir}/firststart.sh

	sed -i -e 's:#SYSCONFDIR#:${sysconfdir}:g' \
               -e 's:#SBINDIR#:${sbindir}:g' \
               -e 's:#BASE_BINDIR#:${base_bindir}:g' \
               -e 's:#LOCALSTATEDIR#:${localstatedir}:g' \
               ${D}${sbindir}/*.sh ${D}${sysconfdir}/firststart.d/*
}
