export interface Landmark {

  id: string;

  countryId: string;

  countryName: string;

  cityId: string;

  cityName: string;

  name: string;

  description: string;

  active: boolean;

  importance: 'LOW' | 'MEDIUM' | 'HIGH';

  score: number;

  images: string[];

}
