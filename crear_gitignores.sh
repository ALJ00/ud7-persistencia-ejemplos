#!/bin/bash

# Generador de ficheros .gitignore

# Crea un fichero .gitignore en la carpeta principal y en cada
# proyecto de IntelliJ que encuentre en los subdirectorios
#
# - Usa gibo (https://github.com/simonwhitaker/gibo) para descargar las
#   plantillas desde GitHub
# - Solo mira en el primer nivel de directorios
# - No modifica los ficheros que ya existan

if [ ! -e .gitignore ]; then
	gibo macos windows linux > .gitignore
fi

for d in ./*; do
	if [ -d "$d" ]; then
		for p in "$d"/*; do
			if [ -d "$p" ] && [ -e "$p"/.idea ]; then
				if [ ! -e "$p"/.gitignore ]; then
					gibo jetbrains java > "$p"/.gitignore
			  	fi
		  	fi
		done
  	fi
done
