export enum Role {
  STAFF = 0,
  ADMIN = 1,
}

export enum BanStatus {
  NORMAL = 0,
  BANNED = 1,
}

export interface User {
  userId: number;
  userName: string;
  displayName?: string | null;
  email?: string | null;
  password?: string | null;
  role: Role;
  banStatus: BanStatus;
  gender: number;
  dateOfBirth?: Date | null;
  userCreateDate: Date;
  info?: string | null;
  phoneNumber?: string | null;
  isVerified: boolean;
  googleId?: string | null;
  avatarGoogle?: string | null;
}

export interface UserGoogle {
  id: string;
  email: string;
  given_name?: string;
  family_name?: string;
  name?: string;
  picture?: string;
  verified_email: boolean;
}