import { IonCol, IonGrid, IonInput, IonRow } from '@ionic/react';
import { useEffect, useState } from 'react';
import TableContainer from '../comp/table/TableContainer';
import './Tab2.css';

const Liste_avions: React.FC = () => {

  var header=[{header: "matricule", col: "matricule"},{header: "modèle", col: "model.model"},{header: "marque", col: "model.marque.marque"}];
  
  var additional_col=[
    {header: "",column: "détails", link: "details",params: ["id"]}
  ]


  const [avions, setavions] = useState([]);


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
