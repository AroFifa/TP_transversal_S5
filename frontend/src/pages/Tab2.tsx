import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import Expiration_avion from './Expiration_avion';
import LoginForm from './LoginForm';
import './Tab2.css';

const Tab2: React.FC = () => {
  
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Expiration assurance avion</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">assurance</IonTitle>
          </IonToolbar>
        </IonHeader>
        <Expiration_avion/>
      </IonContent>
    </IonPage>
  );
};

export default Tab2;
