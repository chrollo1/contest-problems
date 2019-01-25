package ECOO;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ECOOReading {

	public static void main(String[] args) throws IOException {

		int[][] arr = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i][j] = new Random().nextInt(5+1);
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
				
		System.exit(0);
	}
	
	static class Reader {

	    final private int BUFFER_SIZE = 1 << 16;
	    private DataInputStream din;
	    private byte[] buffer;
	    private int bufferPointer, bytesRead;
		private PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	    public Reader() {
	        din = new DataInputStream(System.in);
	        buffer = new byte[BUFFER_SIZE];
	        bufferPointer = bytesRead = 0;
	    }

	    public Reader(String file_name) throws IOException {
	        din = new DataInputStream(new FileInputStream(file_name));
	        buffer = new byte[BUFFER_SIZE];
	        bufferPointer = bytesRead = 0;
	    }

	    public String readLine() throws IOException {
	        byte[] buf = new byte[64]; // line length
	        int cnt = 0, c;
	        while ((c = read()) != -1) {
	            if (c == '\n') {
	                break;
	            }
	            buf[cnt++] = (byte) c;
	        }
	        return new String(buf, 0, cnt);
	    }

	    public int nextInt() throws IOException {
	        int ret = 0;
	        byte c = read();
	        while (c <= ' ') {
	            c = read();
	        }
	        boolean neg = (c == '-');
	        if (neg) {
	            c = read();
	        }
	        do {
	            ret = ret * 10 + c - '0';
	        } while ((c = read()) >= '0' && c <= '9');

	        if (neg) {
	            return -ret;
	        }
	        return ret;
	    }

	    public long nextLong() throws IOException {
	        long ret = 0;
	        byte c = read();
	        while (c <= ' ') {
	            c = read();
	        }
	        boolean neg = (c == '-');
	        if (neg) {
	            c = read();
	        }
	        do {
	            ret = ret * 10 + c - '0';
	        } while ((c = read()) >= '0' && c <= '9');
	        if (neg) {
	            return -ret;
	        }
	        return ret;
	    }

	    public double nextDouble() throws IOException {
	        double ret = 0, div = 1;
	        byte c = read();
	        while (c <= ' ') {
	            c = read();
	        }
	        boolean neg = (c == '-');
	        if (neg) {
	            c = read();
	        }

	        do {
	            ret = ret * 10 + c - '0';
	        } while ((c = read()) >= '0' && c <= '9');

	        if (c == '.') {
	            while ((c = read()) >= '0' && c <= '9') {
	                ret += (c - '0') / (div *= 10);
	            }
	        }

	        if (neg) {
	            return -ret;
	        }
	        return ret;
	    }

	    private void fillBuffer() throws IOException {

	        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	        if (bytesRead == -1) {
	            buffer[0] = -1;
	        }
	    }

	    private byte read() throws IOException {
	        if (bufferPointer == bytesRead) {
	            fillBuffer();
	        }
	        return buffer[bufferPointer++];
	    }


		private void print(Object o) {
			pr.print(o);
		}

		private void println(Object o) {
			pr.println(o);
		}

		private void flush() {
			pr.flush();
		}

		private void println() {
			pr.println();
		}

		private void exit() throws IOException {
			din.close();
			pr.close();
			System.exit(0);
		}
	}
}
