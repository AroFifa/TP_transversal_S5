import { IonBackButton, IonCol, IonGrid, IonRow } from '@ionic/react';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import FicheContainer from '../comp/fiche/FicheContainer';
import TableContainer from '../comp/table/TableContainer';
import './Tab3.css';

const Details_avion: React.FC = () => {
    const [data, setdata] = useState();


    const params:any=useParams();
    
    useEffect(() => {
        var content = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
        };
    
        fetch("http://localhost:8080/avions/"+params.id+"/kilometrages", content)
        .then((response) => {
            if (response.status === 400) {
            alert("Erreur");
            } else return response.json();
        })
        .then((json) => {
            if("error" in json){
                console.log(json);
              alert(json.error.message);}
            else{
                setdata(json.data)
                
            }

            
        });
    }, []);



    

    var info={title: "Détails"}
    var toShow=[
        {label: "matricule",col: "matricule"},
        {label: "Type",col: "type.type"},
        {label: "Modèle",col: "model.model"},
        {label: "Marque",col: "model.marque.marque"},
        {label: "Année",col: "annee"},
        {label: "image",col: "image"}
    ]

    var header=[{header: "date", col: "date"},{header: "Debut km", col: "debut_km"},{header: "Fin km", col: "fin_km"}];
  
    var dataD:any=data;
    
    if(data===undefined)
        return(<></>);
    else
        return (
            <IonGrid>
                <IonRow>
                
                    <IonCol size='15px'><IonBackButton defaultHref='/tab1'></IonBackButton></IonCol>
                </IonRow>
                <IonRow>
                    <IonCol>
                        <FicheContainer info={info} object={data} toshow={toShow}/>
                    </IonCol>
                    <IonCol><TableContainer header={header} data={dataD.parcours}/></IonCol>
                </IonRow>
            </IonGrid>

        );
};

export default Details_avion;
