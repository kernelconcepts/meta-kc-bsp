

PACKAGECONFIG_DEFAULT = "dbus udev evdev tools libs freetype sql-sqlite"
PACKAGECONFIG_SCREEN ?= "${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'gui widgets accessibility tests', '', d)}"

PACKAGECONFIG_append = " \
    ${PACKAGECONFIG_SCREEN} \
"

PACKAGECONFIG[gui] = "-gui,-no-gui"
#QT_CONFIG_FLAGS += " "


OE_QMAKE_PATH_QT_FONTS = "${datadir}/fonts/truetype"

pkg_postinst_ontarget_${PN} () {
	# make sure Qt apps find standard fonts
	ln -sf /usr/share/fonts/truetype/ /usr/lib/fonts
}