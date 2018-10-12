SUMMARY = "Utitlites fpr working with OverlayFS"
HOMEPAGE = "https://github.com/kmxz/overlayfs-tools"
SECTION = "Applications/System"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f312a7c4d02230e8f2b537295d375c69"

DEPENDS = "attr"

SRCREV = "0d44989f1ab7f2e0f565e58f9aff7a9cffb32cd7"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${S}"

SRC_URI = "git://github.com/kmxz/overlayfs-tools \
           file://0001-Drop-hard-coded-compiler-stuff.patch \
           file://0002-Fix-linking.patch \
           "

inherit base

do_configure () {
}

do_compile () {
	oe_runmake overlay
}

do_install() {
	install -d ${D}${bindir}
	install -m 644 ${B}/overlay ${D}/${bindir}/overlay
}

