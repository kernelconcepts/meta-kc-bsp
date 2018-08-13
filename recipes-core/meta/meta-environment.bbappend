# Create custom settings script to become included by environment script

fakeroot do_create_kc_sdk_file() {
	mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
	script=${D}${SDKPATHNATIVE}/environment-setup.d/kcbsp.sh

	echo '# KC SDK settings section' > $script
	echo 'basename -- "$0$ZSH_VERSION" | grep -q "^environment-setup$" && echo "$SHELL" | grep -q "/\(\([bd]ash\)\|\([k]sh\)\)$" && ENV=$0 POSIXLY_CORRECT=1 exec $SHELL -i' >> $script
	echo 'basename -- "$0$ZSH_VERSION" | grep -q "^environment-setup$" && echo "Usage: . $0" && exit 1' >> $script
	echo 'echo "Applying environment settings..."' >> $script
	echo 'echo "Version:       ${DISTRO_VERSION}"' >> $script
	echo 'echo "Target prefix: ${TARGET_PREFIX}"' >> $script
	echo 'export PS1="[$ARCH] $PS1"' >> $script

	echo 'SDK_PATH=$OECORE_NATIVE_SYSROOT/usr' >> $script
	echo 'alias opkg-target="LD_LIBRARY_PATH=$SDK_PATH/lib $SDK_PATH/bin/opkg-cl -f $SDKTARGETSYSROOT/etc/opkg.conf -o $SDKTARGETSYSROOT"' >> $script

	sed -i -e 's:${SDKPATHNATIVE}:$OECORE_NATIVE_SYSROOT:g' ${script}
}

# Deploy generic link for environment setup

do_install_append () {
	mkdir -p ${D}/${SDKPATH}/
	cd ${D}/${SDKPATH}/
	ln -sf environment-setup-${REAL_MULTIMACH_TARGET_SYS} environment-setup
	cd -
}

FILES_${PN} += " \
	${SDKPATH}/environment-setup \
	"

addtask create_kc_sdk_file after do_install before do_package

