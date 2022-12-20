import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import Details_vehicule from './Details_avion';
import './Authentification.css';
import FormContainer from '../comp/form/FormContainer';
import { logIn } from 'ionicons/icons';
import { useState } from 'react';
import { useParams } from 'react-router';


const Authentification: React.FC = () => {


    const params:any=useParams();
    const [idAvion,setIdAvion]=useState(params.id);
    const [email,setEmail]=useState('');
    const [passwd,setPasswd]=useState('');

    
  const login = async (e: React.FormEvent) => {
    e.preventDefault();
    var emailA:any=email;
    var pwdA:any=passwd;
    

    var content = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: ""
    };

    content.body = JSON.stringify({
      email: emailA.detail.value,
      mdp: pwdA.detail.value
    });



    fetch("http://localhost:8080/login", content)
      .then((response) => {
        if (response.status === 400) {
          alert("Mot de passe non identifier");
        } else return response.json();
      })
      .then((json) => {
          if("error" in json){
            alert(json.error.message);}
          else{

            const token_resp="Bearer "+json.data;

            sessionStorage.setItem("bearer",token_resp);
            window.location.href="/details/"+idAvion;
          }

          
      });

    
  };



    
  var info={title: ""}
  var fields=[
    {label: "",name: "id_avion",type:"hidden",placeholder: "", onchange: setIdAvion},
    {label: "Adresse e-mail",name: "email",type:"email", defaultValue: "rakoto@gmail.com",placeholder: "email", onchange: setEmail},
    {label: "Mot de passe",name: "passwd",type:"password", defaultValue: "mdp",placeholder: "password", onchange: setPasswd}
  ]
  var button={label: "Se connecter",icon: {logIn} }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Authentification</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Login</IonTitle>
          </IonToolbar>
        </IonHeader>
        
        <FormContainer info={info} fields={fields} button={button} onsubmit={login}/>
      </IonContent>
    </IonPage>
  );
};

export default Authentification;
