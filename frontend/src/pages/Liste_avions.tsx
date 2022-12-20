import { IonCol, IonGrid, IonInput, IonRow } from '@ionic/react';
import { useEffect, useState } from 'react';
import CardContainer from '../comp/card/CardContainer';
import TableContainer from '../comp/table/TableContainer';
import './Tab2.css';

const Liste_avions: React.FC = () => {

  var header=[{label: "matricule", col: "matricule"},{label: "modèle", col: "modele.modele"},{label: "marque", col: "matricule"}];
  
  var additional_col=[
    {label: "",column: "détails", link: "login",params: ["id"]}
  ]


  const [avions, setavions] = useState([]);

  console.log(avions);
  


  useEffect(() => {
    var content = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    };
  
    fetch("http://localhost:8080/avions", content)
      .then((response) => {
        if (response.status === 400) {
          alert("Erreur");
        } else return response.json();
      })
      .then((json) => {
        if("error" in json)
          alert(json.error.message);
        else
          setavions(json.data)

          
          
        
      });
  }, []);


  

  return (
    <IonGrid>
      <IonRow>
        <IonCol>
          <TableContainer header={header} data={avions} additional_column={additional_col} />
          </IonCol>
        

      </IonRow>
    </IonGrid>
  );
};

export default Liste_avions;
