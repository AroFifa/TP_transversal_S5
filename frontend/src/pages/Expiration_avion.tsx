import { IonButton, IonCol, IonGrid, IonIcon, IonInput, IonItem, IonLabel, IonRange, IonRow, IonTitle } from '@ionic/react';
import { colorFilter, fileTrayStackedSharp, filter, filterCircle, filterSharp, search } from 'ionicons/icons';
import { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router';
import TableContainer from '../comp/table/TableContainer';
import './Tab2.css';

const Expiration_avion: React.FC = () => {
  const { URLSearchParams } = window;

  const location=useLocation();
  const params = new URLSearchParams(location.search);

  // Get the value of a specific parameter
  const mois = params.get('mois');
  const [data, setData] = useState([]);

  

  var header=[{label: "matricule", col: "matricule"},{label: "modèle", col: "modele.modele"},{label: "marque", col: "matricule"},{label: "Dernière payement",col:"payement_assurance.date_payement"},{label: "Date d'expiration",col :"payement_assurance.date_expiration"}];

  var additional_col=[
    {label: "",column: "détails", link: "login",params: ["id"]}
  ]

  var m1=(mois!==undefined)?Number(mois):0;


  useEffect(() => {
    var content = {
    method: "GET",
    headers: {
        "Content-Type": "application/json",
    },
    };

    fetch("http://localhost:8080/avions/assurance?mois="+m1, content)
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
            setData(json.data)
            
        }

        
    });
}, []);





  var m=(mois!==undefined)?Number(mois):1;
  

  return (
    <IonGrid>
        <IonRow>
            <IonTitle>Date d'expiration d'assurance</IonTitle>
        </IonRow>
        <form >
      <IonRow>
        <IonCol>
            <IonItem>
                <IonLabel>Dans</IonLabel>
                    <IonRange min={1} max={24} value={m} pin={true} name="mois" ticks={true} snaps={true}></IonRange>                <IonLabel>mois</IonLabel>
                <IonButton type='submit' fill='outline' expand='block'><IonIcon icon={filter}></IonIcon></IonButton>
            </IonItem>
        </IonCol>
        <IonCol></IonCol>
        <IonCol></IonCol>
      </IonRow>
        </form>
      <IonRow>
        <IonCol>
          <TableContainer header={header} data={data} additional_column={additional_col} />
          </IonCol>
        

      </IonRow>
    </IonGrid>
  );
};

export default Expiration_avion;
