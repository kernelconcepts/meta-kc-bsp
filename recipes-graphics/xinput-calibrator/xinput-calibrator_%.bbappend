# Disable automatic start
# usually not required any more
do_install_append() {
     rm -rf  ${D}${sysconfdir}/X11/Xsession.d
     rm -rf ${D}${sysconfdir}/xdg/autostart
}
