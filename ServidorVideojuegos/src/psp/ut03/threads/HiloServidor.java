package psp.ut03.threads;

import psp.ut03.ModeloVideojuegos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HiloServidor extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private ModeloVideojuegos modeloVideojuegos;

    public HiloServidor(ServerSocket serverSocket, ExecutorService pool, ModeloVideojuegos modeloVideojuegos) {
        this.serverSocket = serverSocket;
        this.pool = pool;
        this.modeloVideojuegos = modeloVideojuegos;
    }

    @Override
    public void run() {
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                Hi
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

