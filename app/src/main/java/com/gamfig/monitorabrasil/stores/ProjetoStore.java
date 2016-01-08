package com.gamfig.monitorabrasil.stores;


import com.gamfig.monitorabrasil.actions.Action;
import com.gamfig.monitorabrasil.actions.ProjetoActions;
import com.gamfig.monitorabrasil.dispatcher.Dispatcher;
import com.parse.ParseObject;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geraldo on 13/08/2015.
 */
public class ProjetoStore extends Store{

    private static ProjetoStore instance;
    private String status;

    public String getEvento() {
        return evento;
    }

    private String evento;
    private List<ParseObject> projetos;
    private ParseObject projeto;

    protected ProjetoStore(Dispatcher dispatcher) {
        super(dispatcher);
        projetos = new ArrayList<>();
    }

    public static ProjetoStore get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ProjetoStore(dispatcher);
        }
        return instance;
    }

    public ParseObject getProjeto(){return projeto;}

    public List<ParseObject> getProjetos(){
        return projetos;
    }


    @Override
    @Subscribe
    public void onAction(Action action) {
        status = "erro";
        this.evento = action.getType();

        switch (action.getType()) {
            case ProjetoActions.PROJETO_GET_TODOS:
                projetos = ((List<ParseObject>) action.getData().get(ProjetoActions.KEY_TEXT));
                emitStoreChange();
                break;


        }
    }

    @Override
    StoreChangeEvent changeEvent() {
        ProjetoStoreChangeEvent mProjetoStoreChangeEvent = new ProjetoStoreChangeEvent();
        return mProjetoStoreChangeEvent;
    }

    public void limpaProjetos() {

            projetos.clear();

    }


    public class ProjetoStoreChangeEvent implements StoreChangeEvent {

    }
}
