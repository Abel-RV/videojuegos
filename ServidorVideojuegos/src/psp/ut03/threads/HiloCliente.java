package psp.ut03.threads;

import psp.ut03.ModeloVideojuegos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread {
    private Socket socket;
    private ModeloVideojuegos modeloVideojuegos;

    public HiloCliente(Socket socket, ModeloVideojuegos modeloVideojuegos) {
        this.socket = socket;
        this.modeloVideojuegos = modeloVideojuegos;
    }

    @Override
    public void run() {
        try(
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ){
            String comando;
            while(true){
                comando = dis.readUTF();
                if(comando.equalsIgnoreCase("END")){
                    break;
                }

                switch (comando) {
                    case "GET-GEN"->{
                        ArrayList<String>= modeloVideojuegos.ob
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
