### Installation
[Download Mono and GTK#](https://www.mono-project.com/download/stable/)

Then add the bin folder (C:\Program Files\Mono\bin) to the path variables

### How to use
Compile the client with the command `mcs src/*.cs -pkg:wcf -out:server.exe` if it is not working launch the compile.sh script

Then start the server with `mono server.exe`
