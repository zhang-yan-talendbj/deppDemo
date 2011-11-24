PipedInputStream pipedIS = new PipedInputStream();
PipedOutputStream pipedOS = new PipedOutputStream();

try {
   pipedOS.connect(pipedIS);
}
catch(IOException e) {
   System.err.println("¡¨Ω” ß∞‹");
   System.exit(1);
}

PrintStream ps = new PrintStream(pipedOS);
System.setOut(ps);
System.setErr(ps);
