package com.banana.Bean;
import com.banana.Model.Local;
import com.banana.Model.ReservaSala;
import com.banana.Model.Sala;
import com.banana.Service.Impl.LocalServiceImpl;
import com.banana.Service.Impl.ReservaSalaServiceImpl;
import com.banana.Service.Impl.SalaServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("reservaSalaBean")
@ViewScoped
public class ReservaSalaBean implements Serializable {
    private Date dataInicio;
    private Date horaInicio;
    private Date dataFim;
    private Date horaFim;
    List<SelectItem> locais;
    List<SelectItem> salas;
    private boolean cafe;
    private int quantidadePessoas;
    private String descricao;
    private int IDLocal;
    private int IDSala;
    private Date dataBusca;
    private int IDSalaBusca;

    @Inject
    transient private ReservaSalaServiceImpl reservaSalaService;
    @Inject
    transient private SalaServiceImpl salaService;
    @Inject
    transient private LocalServiceImpl localService;

    public String salvarReservaSala( ){
        System.out.println("hora fim: " +  horaFim.getTime());
        System.out.println("hora ini: " +horaInicio.getTime());

        if(horaFim.getTime() <= horaInicio.getTime()){
            System.out.println("A hora final deve ser maior que a hora inicial");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "A hora final deve ser maior que a hora inicial"));
            return "";
        }
       if(reservaSalaService.buscarReservaPeriodo(dataInicio,dataFim, IDSala) == true){
           System.out.println("TRUE");

           System.out.println("Já há reserva para este período");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Já há reserva para este período"));
            return "";
        }
       else{
           System.out.println("FALSE");

       }
        reservaSalaService.salvarReservaSala(dataInicio,  dataFim,  horaInicio, horaFim,  IDLocal, IDSala, cafe, quantidadePessoas, descricao);
        return "/restrito/reservasala/reservasalasList.xhtml?faces-redirect=true";
    }
    public String editarReservaSala(int ID){

        reservaSalaService.editarTela(ID);
        return "/restrito/reservasala/editReservaSala.xhtml?faces-redirect=true";
    }
    public String deletarReservaSala(int ID){

        reservaSalaService.deletarReservaSala(ID);
        return "/restrito/reservasala/reservasalasList.xhtml?faces-redirect=true";
    }


    public List<ReservaSala> listarReservaSalas() {
        return reservaSalaService.listarReservaSalas();
    }


    public String editarReservaSala(ReservaSala reservaSala) {
        reservaSalaService.editarReservaSala(reservaSala);
        return "/restrito/reservasala/reservasalasList.xhtml?faces-redirect=true";
    }

    public List<SelectItem> listarLocais() {
        locais = new ArrayList<SelectItem>();
        for (Local l : localService.listarLocais()) {
            locais.add(new SelectItem(l.getID(), l.getNome()));
        }
        return locais;
    }

    public List<SelectItem> listarSalas() {
        salas = new ArrayList<SelectItem>();
        for (Sala sl : salaService.listarSalas()) {
            salas.add(new SelectItem(sl.getID(), sl.getNome()));
        }
        return salas;
    }
    public List<SelectItem> listarSalasByLocalID(int IDLocal) {
        System.out.println("============================");
        System.out.println(IDLocal);
        System.out.println("============================");
        salas = new ArrayList<SelectItem>();
        for (Sala sl :  salaService.listarSalasByLocalID(IDLocal)) {
            salas.add(new SelectItem(sl.getID(), sl.getNome()));
        }

        return salas;
    }



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public List<SelectItem> getLocais() {
        return locais;
    }

    public void setLocais(List<SelectItem> locais) {
        this.locais = locais;
    }

    public List<SelectItem> getSalas() {
        return salas;
    }

    public void setSalas(List<SelectItem> salas) {
        this.salas = salas;
    }

    public boolean isCafe() {
        return cafe;
    }

    public void setCafe(boolean cafe) {
        this.cafe = cafe;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public LocalServiceImpl getLocalService() {
        return localService;
    }

    public void setLocalService(LocalServiceImpl localService) {
        this.localService = localService;
    }

    public int getIDLocal() {
        return IDLocal;
    }

    public void setIDLocal(int IDLocal) {
        this.IDLocal = IDLocal;
    }

    public int getIDSala() {
        return IDSala;
    }

    public void setIDSala(int IDSala) {
        this.IDSala = IDSala;
    }

    public Date getDataBusca() {
        return dataBusca;
    }

    public void setDataBusca(Date dataBusca) {
        this.dataBusca = dataBusca;
    }

    public int getIDSalaBusca() {
        return IDSalaBusca;
    }

    public void setIDSalaBusca(int IDSalaBusca) {
        this.IDSalaBusca = IDSalaBusca;
    }
}
