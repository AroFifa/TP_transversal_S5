import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import Details_avion from './Details_avion';
import './Tab3.css';

const Tab3: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Fiche véhicule</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Détails</IonTitle>
          </IonToolbar>
        </IonHeader>
        <Details_avion />
      </IonContent>
    </IonPage>
  );
};

export default Tab3;
