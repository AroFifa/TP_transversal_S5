import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import Details_vehicule from './Details_avion';
import './Authentification.css';
import FormContainer from '../comp/form/FormContainer';
import { logIn } from 'ionicons/icons';
import { useState } from 'react';


const Authentification: React.FC = () => {


    const [isValid,setIsValid]=useState<boolean>();
    const [isTouched,setIsTouched]=useState(false);
    const [email,setEmail]=useState('');
    const [passwd,setPasswd]=useState('');


    const validateEmail = (email: string) => {
        return email.match(
          /^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        );
      };

    const validate=(ev:Event)=>{

        const value=(ev.target as HTMLInputElement).value;

        setIsValid(undefined);

        if(value==='')
            return;

        validateEmail(value) !==null ? setIsValid(true):setIsValid(false);
        

    }
    const markTouched=()=>{
        setIsTouched(true);
    }
    
  const login = async (e: React.FormEvent) => {
    e.preventDefault();
    console.log(email);
    console.log(passwd);


    var content = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: ""
    };

    content.body = JSON.stringify({
      email: email,
      pwd: passwd,
    });


    fetch("http://localhost:8080/societes/login", content)
      .then((response) => {
        if (response.status === 400) {
          alert("Mot de passe non identifier");
        } else return response.json();
      })
      .then((json) => {
        if (json.length !== 0) setEmail(json);
        else alert("Mot de passe non identifier");
      });


    
  };



    
  var info={title: ""}
  var fields=[
    {label: "Adresse e-mail",name: "email",type:"email",placeholder: "email"},
    {label: "Mot de passe",name: "passwd",type:"password",placeholder: "password"}
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
